package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.converters.ConversionException;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer provides a simple way of doing the input of a short number. The number is read form a text input field and parsed
 * with {@link Short#parseShort(java.lang.String, int)} were the second argument is the value given in the
 * {@linkplain IntegerInputRenderer#setBase(int) base} property.
 * 
 * <p>
 * Example: <input type="text" value="12345"/>
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(mode = RenderMode.INPUT, type = Short.class), @Renderer(mode = RenderMode.INPUT, type = short.class) })
public class ShortInputRenderer extends IntegerInputRenderer {
	@Override
	protected Converter getConverter() {
		return new ShortNumberConverter(getBase());
	}

	private class ShortNumberConverter extends Converter {

		private int base;

		public ShortNumberConverter(int base) {
			this.base = base;
		}

		public int getBase() {
			return this.base;
		}

		@Override
		public Object convert(Class type, Object value) {
			String numberText = ((String) value).trim();

			if (numberText.length() == 0) {
				return null;
			}

			try {
				return Short.parseShort(numberText.trim(), getBase());
			} catch (NumberFormatException e) {
				throw new ConversionException("renderers.converter.short", e, true, value);
			}
		}

	}
}
