package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.converters.FloatNumberConverter;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * {@inheritDoc}
 * 
 * This renderer converts the value to a float with {@link Float#parseFloat(java.lang.String)}.
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(mode = RenderMode.INPUT, type = Float.class), @Renderer(mode = RenderMode.INPUT, type = float.class) })
public class FloatInputRenderer extends NumberInputRenderer {

	@Override
	protected Converter getConverter() {
		return new FloatNumberConverter();
	}

}
