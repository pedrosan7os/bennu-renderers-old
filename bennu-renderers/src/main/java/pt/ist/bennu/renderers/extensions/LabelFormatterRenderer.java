package pt.ist.bennu.renderers.extensions;

import java.util.Properties;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.OutputRenderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.extensions.util.RendererMessageResourceProvider;
import pt.ist.bennu.renderers.util.LabelFormatter;

@Renderer(type = LabelFormatter.class, properties = {
		@RendererProperty(name = "bundleName(application)", value = "APPLICATION_RESOURCES"),
		@RendererProperty(name = "bundleName(enum)", value = "ENUMERATION_RESOURCES"),
		@RendererProperty(name = "bundleName(default)", value = "APPLICATION_RESOURCES") })
public class LabelFormatterRenderer extends OutputRenderer {

	private final Properties bundleMappings;

	public LabelFormatterRenderer() {
		super();

		this.bundleMappings = new Properties();

	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {

				return new HtmlText(((LabelFormatter) object).toString(new RendererMessageResourceProvider(
						LabelFormatterRenderer.this.bundleMappings)));
			}

		};
	}

	/**
	 * 
	 * 
	 * @property
	 */
	public void setBundleName(String bundle, String name) {
		this.bundleMappings.put(bundle, name);
	}

	public String getBundleName(String bundle) {
		return this.bundleMappings.getProperty(bundle);
	}

}
