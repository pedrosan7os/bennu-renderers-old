package pt.ist.bennu.renderers.core;

import org.apache.commons.collections.Predicate;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlContainer;
import pt.ist.bennu.renderers.core.components.HtmlInlineContainer;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.components.HtmlTextInput;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.model.MetaSlotKey;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer provides a standard way of doing the input of a string. The string is read with a text input field.
 * 
 * <p>
 * Example: <input type="text" value="the string"/>
 * 
 * @author cfgi
 */
@Renderer(mode = RenderMode.INPUT, type = String.class)
public class StringInputRenderer extends TextFieldRenderer {

	@Override
	protected HtmlComponent createTextField(Object object, Class type) {
		String string = (String) object;

		HtmlTextInput input = new HtmlTextInput();
		input.setValue(string);

		HtmlContainer container = new HtmlInlineContainer();
		container.addChild(input);
		container.addChild(new HtmlText(getFormatLabel()));

		return container;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new StringInputFieldLayout();
	}

	class StringInputFieldLayout extends TextFieldLayout {

		@Override
		protected void setContextSlot(HtmlComponent component, MetaSlotKey slotKey) {
			HtmlComponent actualComponent = component instanceof HtmlTextInput ? component : component.getChild(new Predicate() {

				@Override
				public boolean evaluate(Object arg0) {
					return arg0 instanceof HtmlTextInput;
				}

			});
			super.setContextSlot(actualComponent, slotKey);
		}

		@Override
		public void applyStyle(HtmlComponent component) {
			HtmlComponent actualComponent = component instanceof HtmlTextInput ? component : component.getChild(new Predicate() {

				@Override
				public boolean evaluate(Object arg0) {
					return arg0 instanceof HtmlTextInput;
				}

			});
			super.applyStyle(actualComponent);
		}

	}
}
