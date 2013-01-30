package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;

/**
 * This renderer provides a generic presentation for a number. The number is simply converted to a string and presented.
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(type = float.class), @Renderer(type = double.class) })
public class NumberRenderer extends OutputRenderer {

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				Number number = (Number) object;

				return new HtmlText(String.valueOf(number));
			}

		};
	}
}
