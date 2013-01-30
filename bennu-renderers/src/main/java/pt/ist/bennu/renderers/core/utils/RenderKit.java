package pt.ist.bennu.renderers.core.utils;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import pt.ist.bennu.renderers.core.AbstractRenderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.contexts.PresentationContext;
import pt.ist.bennu.renderers.core.exceptions.NoRendererException;
import pt.ist.bennu.renderers.core.exceptions.NoSuchSchemaException;
import pt.ist.bennu.renderers.core.schemas.Schema;

/**
 * Entry point for the rendering mechanism. The render kit allows several renderers and schemas to be registered. The application
 * can retrieve schemas to create suitable meta objects and prepare a presentation context and then start the rendering mechanism
 * with, for example, an invokation to {@link #render(PresentationContext, Object, Class)}.
 * 
 * @author cfgi
 */
public class RenderKit {

	private static RenderKit instance = new RenderKit();

	private Map<RenderMode, RendererRegistry> registryMap;

	private SchemaRegistry schemaRegistry;

	//
	// construct
	//

	private RenderKit() {
		registryMap = new Hashtable<RenderMode, RendererRegistry>();

		for (RenderMode mode : RenderMode.values()) {
			registryMap.put(mode, new RendererRegistry());
		}

		schemaRegistry = new SchemaRegistry();
	}

	/**
	 * Used to allow reloading of the configuration in runtime All registered schemas and renderers are lost.
	 */
	public static void reset() {
		RenderKit.instance = new RenderKit();
	}

	/**
	 * Retrieves the default instance of the render kit.
	 */
	public static RenderKit getInstance() {
		return instance;
	}

	//
	// register renderer and schema
	//

	/**
	 * Registers a new renderer for the mode type and layout specified. Every instance of this renderer will be pre-configured
	 * with the properties given so the work as the default properties for the renderer.
	 * <p>
	 * An instace of the renderer can be retrieved with {@link #getRenderer(RenderMode, Class, String)}.
	 */
	public void registerRenderer(RenderMode mode, Class type, String layout, Class<? extends AbstractRenderer> renderer,
			Properties defaultProperties) {
		registryMap.get(mode).registerRenderer(type, layout, renderer, defaultProperties);
	}

	/**
	 * Registers a new schema. The scheme can be searched by name with {@link #findSchema(String)}.
	 */
	public void registerSchema(Schema schema) {
		schemaRegistry.registerSchema(schema);
	}

	//
	// find
	//

	/**
	 * Finds a registered schema by name. If the name is null then this method returns null. In all other cases returns the schema
	 * with the given name or throws an exception if the schema is not registered.
	 * 
	 * @exception NoSuchSchemaException
	 *                if the schema named <tt>schemaName</tt> could not be found
	 */
	public Schema findSchema(String schemaName) {
		return schemaRegistry.getSchema(schemaName);
	}

	/**
	 * @exception NoRendererException
	 *                if no renderer description could be found
	 */
	public RendererDescription getExactRendererDescription(RenderMode mode, Class type, String layout) {
		return registryMap.get(mode).getExactRenderDescription(type, layout);
	}

	/**
	 * @exception NoRendererException
	 *                if no renderer description could be found
	 */
	public RendererDescription getRendererDescription(RenderMode mode, Class type, String layout) {
		return registryMap.get(mode).getRenderDescription(type, layout);
	}

	/**
	 * @exception NoRendererException
	 *                if no specific renderer description could be found
	 */
	private RendererDescription getSpecificRendererDescription(RenderMode mode, Class type, String layout) {
		try {
			return getRendererDescription(mode, type, layout);
		} catch (NoRendererException e) {
			return getRendererDescription(mode, type, null);
		}
	}

	/**
	 * Allows to retrieve a new instance of a renderer previously registered for the given mode, type, and layout.
	 * 
	 * @return a new instance of the renderer pre-configured with the default properties
	 */
	public AbstractRenderer getRenderer(RenderMode mode, Class type, String layout) {
		RendererDescription rendererDescription = getSpecificRendererDescription(mode, type, layout);
		return rendererDescription.createRenderer();
	}

	/**
	 * This method is a convenience method. It works in a similar way as {@link #getRenderer(RenderMode, Class, String)} but the
	 * render mode is retrieved from the presentation context.
	 */
	public AbstractRenderer getRenderer(PresentationContext context, Class type, String layout) {
		return getRenderer(context.getRenderMode(), type, layout);
	}

	//
	// render
	//

	private void prepareRenderer(AbstractRenderer renderer, PresentationContext context) {
		renderer.setContext(context);

		Properties properties = context.getProperties();

		if (properties != null) {
			RenderUtils.setProperties(renderer, properties);
		}
	}

	/**
	 * Renders the object given as <code>targetObject</code>.
	 * 
	 * @exception NullPointerException
	 *                if the targetObject is <code>null</code>
	 * @exception NoRendererException
	 *                if no suitable renderer could be found to present the <code>object</code> with the given context
	 */
	public HtmlComponent render(PresentationContext context, Object object) {
		return render(context, object, object.getClass());
	}

	/**
	 * Usefull for presenting the <code>null</code> value of a specific type or to present an object as one of it's superclasses.
	 */
	public HtmlComponent render(PresentationContext context, Object object, Class type) {
		AbstractRenderer renderer = getRenderer(context, type, context.getLayout());

		return renderUsing(renderer, context, object, type);
	}

	/**
	 * Uses the given renderer to render the object. That is setups the renderer and invokes
	 * {@link AbstractRenderer#render(Object, Class)}.
	 */
	public HtmlComponent renderUsing(AbstractRenderer renderer, PresentationContext context, Object object, Class type) {
		prepareRenderer(renderer, context);

		return renderer.render(object, type);
	}
}
