package pt.ist.bennu.dispatch;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.bennu.core.domain.groups.AuthorizationException;
import pt.ist.bennu.core.domain.groups.PersistentGroup;
import pt.ist.bennu.dispatch.model.ApplicationInfo;
import pt.ist.bennu.dispatch.model.FunctionalityInfo;
import pt.ist.bennu.renderers.annotation.Mapping;
import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.AbstractRenderer;
import pt.ist.bennu.renderers.core.exceptions.NoRendererException;
import pt.ist.bennu.renderers.core.utils.RenderKit;
import pt.ist.bennu.renderers.core.utils.RenderMode;
import pt.ist.bennu.renderers.struts.StrutsAnnotationsPlugIn;

import com.google.common.base.Strings;

@HandlesTypes({ Mapping.class, Renderer.class, Renderers.class })
public class RenderersAnnotationProcessor implements ServletContainerInitializer {
	private static final Logger logger = LoggerFactory.getLogger(RenderersAnnotationProcessor.class);

	private static Map<String, String> forwards = new HashMap<>();

	private static Map<String, String> authorizations = new HashMap<>();

	@Override
	@SuppressWarnings("unchecked")
	public void onStartup(Set<Class<?>> classes, ServletContext context) throws ServletException {
		if (classes != null) {
			Map<Class<?>, ApplicationInfo> apps = new HashMap<>();
			for (Class<?> type : classes) {
				Mapping mapping = type.getAnnotation(Mapping.class);
				if (mapping != null) {
					StrutsAnnotationsPlugIn.registerMapping(type);

					for (Method method : type.getMethods()) {
						Functionality functionality = method.getAnnotation(Functionality.class);
						if (functionality != null) {
							extractFunctionality(apps, functionality, method);
							String function =
									functionality.app().getAnnotation(Application.class).path() + "/" + functionality.path();
							forwards.put(function, mapping.path() + ".do?method=" + method.getName());
							authorizations.put(function, functionality.group());
						}
					}
				}

				Renderer renderer = type.getAnnotation(Renderer.class);
				if (renderer != null) {
					processRenderer((Class<? extends AbstractRenderer>) type, renderer);
				}
				Renderers renderers = type.getAnnotation(Renderers.class);
				if (renderers != null) {
					for (Renderer innerRenderer : renderers.value()) {
						processRenderer((Class<? extends AbstractRenderer>) type, innerRenderer);
					}
				}
			}
			for (ApplicationInfo application : apps.values()) {
				AppServer.registerApp(application);
			}
		}
	}

	private void extractFunctionality(Map<Class<?>, ApplicationInfo> apps, Functionality functionality, Method method) {
		if (!apps.containsKey(functionality.app())) {
			extractApp(apps, functionality.app());
		}
		apps.get(functionality.app()).addFunctionality(
				new FunctionalityInfo(functionality.bundle(), functionality.title(), functionality.description(), functionality
						.path(), functionality.group()));
	}

	private void extractApp(Map<Class<?>, ApplicationInfo> apps, Class<?> app) {
		Application application = app.getAnnotation(Application.class);
		if (application != null) {
			apps.put(app, new ApplicationInfo(application.bundle(), application.title(), application.description(),
					"render.do?function=" + application.path(), application.group()));
		} else {
			throw new Error();
		}
	}

	private void processRenderer(Class<? extends AbstractRenderer> type, Renderer renderer) {
		if (logger.isWarnEnabled()) {
			if (hasRenderer(renderer.layout(), renderer.type(), renderer.mode())) {
				logger.warn(String.format("[%s] duplicated definition for type '%s' and layout '%s'", renderer.mode().name(),
						renderer.type(), renderer.layout()));
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("[%s] registering renderer: %s for type '%s' and layout '%s'", renderer.mode().name(),
					type.getName(), renderer.type().getName(), renderer.layout()));
		}
		Properties properties = new Properties();
		for (RendererProperty property : renderer.properties()) {
			properties.setProperty(property.name(), property.value());
		}
		RenderKit.getInstance().registerRenderer(renderer.mode(), renderer.type(),
				Strings.isNullOrEmpty(renderer.layout()) ? null : renderer.layout(), type, properties);
	}

	private static boolean hasRenderer(String layout, Class<?> type, RenderMode mode) {
		try {
			return RenderKit.getInstance().getExactRendererDescription(mode, type, layout) != null;
		} catch (NoRendererException e) {
			return false;
		}
	}

	public static String resolveForward(String function) throws AuthorizationException {
		if (authorizations.containsKey(function)) {
			PersistentGroup.parse(authorizations.get(function)).verify();
			return forwards.get(function);
		}
		throw AuthorizationException.badAccessGroupConfiguration();
	}
}
