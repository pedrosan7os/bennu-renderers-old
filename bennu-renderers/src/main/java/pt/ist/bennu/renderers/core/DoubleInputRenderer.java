package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.converters.ConversionException;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * {@inheritDoc}
 * 
 * This renderer converts the value to a float with {@link Double#parseDouble(java.lang.String)}.
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(mode = RenderMode.INPUT, type = Double.class), @Renderer(mode = RenderMode.INPUT, type = double.class) })
public class DoubleInputRenderer extends NumberInputRenderer {

	@Override
	protected Converter getConverter() {
		return new DoubleNumberConverter();
	}

	private class DoubleNumberConverter extends Converter {

		@Override
		public Object convert(Class type, Object value) {
			String numberText = ((String) value).trim();

			if (numberText.length() == 0) {
				return null;
			}

			try {
				return Double.parseDouble(numberText);
			} catch (NumberFormatException e) {
				throw new ConversionException("renderers.converter.double", e, true, value);
			}
		}
	}
}
