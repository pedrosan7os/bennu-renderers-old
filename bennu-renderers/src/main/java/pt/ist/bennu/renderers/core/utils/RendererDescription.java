package pt.ist.bennu.renderers.core.utils;

import java.util.Properties;

import pt.ist.bennu.renderers.core.AbstractRenderer;

/**
 * RendererDescription is used to mantain the renderer's class and the default properties associated with that particular
 * renderer.
 * 
 * @author cfgi
 */
public class RendererDescription {
	private Class<? extends AbstractRenderer> renderer;

	private Properties properties;

	public RendererDescription(Class<AbstractRenderer> renderer, Properties defaultProperties) {
		this.renderer = renderer;
		this.properties = defaultProperties;
	}

	public Properties getProperties() {
		return properties;
	}

	public Class<? extends AbstractRenderer> getRenderer() {
		return renderer;
	}

	public AbstractRenderer createRenderer() {
		AbstractRenderer renderer = null;

		try {
			renderer = getRenderer().newInstance();

			if (properties != null) {
				RenderUtils.setProperties(renderer, properties);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return renderer;
	}
}