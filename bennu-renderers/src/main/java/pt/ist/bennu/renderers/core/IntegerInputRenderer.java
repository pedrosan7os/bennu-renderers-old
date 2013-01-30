package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.converters.IntegerNumberConverter;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer provides a simple way of doing the input of a integer number. The number is read form a text input field and
 * parsed with {@link Integer#parseInt(java.lang.String, int)} were the second argument is the value given in the
 * {@linkplain #setBase(int) base} property.
 * 
 * <p>
 * Example: <input type="text" value="12345"/>
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(mode = RenderMode.INPUT, type = Integer.class), @Renderer(mode = RenderMode.INPUT, type = int.class),
		@Renderer(mode = RenderMode.INPUT, type = Number.class) })
public class IntegerInputRenderer extends NumberInputRenderer {

	private int base = 10;

	/**
	 * The base in which the number should be interpreted. For instance, if <tt>base</tt> is 16 then an input like <tt>CAFE</tt>
	 * will be interpreted as 51966.
	 * 
	 * @property
	 */
	public void setBase(int base) {
		this.base = base;
	}

	public int getBase() {
		return this.base;
	}

	@Override
	protected Converter getConverter() {
		return new IntegerNumberConverter(getBase());
	}

}
