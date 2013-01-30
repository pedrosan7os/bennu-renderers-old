package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlContainer;
import pt.ist.bennu.renderers.core.components.HtmlFormComponent;
import pt.ist.bennu.renderers.core.components.HtmlInlineContainer;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.components.converters.Converter;

/**
 * This renderer provides a simple way of doing the input of numbers. The value is read with an text input field and converted to
 * the appropriate type.
 * 
 * <p>
 * Example: <input type="text" value="10"/>
 * 
 * @author cfgi
 */
public abstract class NumberInputRenderer extends StringInputRenderer {
	@Override
	public HtmlComponent render(Object targetObject, Class type) {
		Number number = (Number) targetObject;

		String text;
		if (number == null) {
			text = "";
		} else {
			text = number.toString();
		}

		return super.render(text, type);
	}

	@Override
	protected HtmlComponent createTextField(Object object, Class type) {
		HtmlContainer fieldComponent = (HtmlContainer) super.createTextField(object, type);

		HtmlFormComponent formComponent = (HtmlFormComponent) fieldComponent.getChildren().get(0);
		formComponent.setConverter(getConverter());

		HtmlContainer container = new HtmlInlineContainer();
		container.addChild(formComponent);
		container.addChild(new HtmlText(getFormatLabel()));
		return container;
	}

	protected abstract Converter getConverter();
}
