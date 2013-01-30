package pt.ist.bennu.renderers.core;

import java.math.BigDecimal;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.converters.ConversionException;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * {@inheritDoc}
 * 
 * This renderer converts the value to a BigDecimal with its string constructor.
 * 
 * @author lepc
 */
@Renderer(mode = RenderMode.INPUT, type = BigDecimal.class)
public class BigDecimalInputRenderer extends NumberInputRenderer {

	@Override
	protected Converter getConverter() {
		return new BigDecimalNumberConverter();
	}

	private class BigDecimalNumberConverter extends Converter {

		@Override
		public Object convert(Class type, Object value) {
			final String numberText = ((String) value).trim().replace(',', '.');
			try {
				return numberText.length() == 0 ? null : new BigDecimal(numberText);
			} catch (NumberFormatException e) {
				throw new ConversionException("renderers.converter.bigdecimal", e, true, value);
			}
		}

	}
}
