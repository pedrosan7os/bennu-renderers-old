package pt.ist.bennu.renderers.extensions;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.StringInputRenderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlContainer;
import pt.ist.bennu.renderers.core.components.HtmlInlineContainer;
import pt.ist.bennu.renderers.core.components.HtmlLink;
import pt.ist.bennu.renderers.core.components.HtmlScript;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.components.HtmlTextInput;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.model.MetaSlotKey;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer provides a way of asking the user for a color using a color picker.
 * 
 * @author cfgi
 */
@Renderer(mode = RenderMode.INPUT, layout = "webcolor", type = String.class, properties = { @RendererProperty(
		name = "size",
		value = "10") })
public class ColorInputRenderer extends StringInputRenderer {

	private static final String BASE_PATH = "/javaScript/picker";

	@Override
	protected HtmlComponent createTextField(Object object, Class type) {
		HtmlContainer fieldComponent = (HtmlContainer) super.createTextField(object, type);

		HtmlTextInput input = (HtmlTextInput) fieldComponent.getChildren().get(0);
		input.setId(getContext().getMetaObject().getKey().toString());

		HtmlLink link = new HtmlLink();
		link.setModuleRelative(false);
		link.setUrl(BASE_PATH + "/img/");

		HtmlScript script = new HtmlScript();
		script.setContentType("text/javascript");
		script.setScript(String.format("new Control.ColorPicker('%s', { IMAGE_BASE : '%s' });", input.getId(),
				link.calculateUrl()));

		HtmlContainer container = new HtmlInlineContainer();

		container.addChild(input);
		container.addChild(new HtmlText(getFormatLabel()));
		container.addChild(script);

		return container;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new ColorPickerLayout();
	}

	class ColorPickerLayout extends TextFieldLayout {

		@Override
		protected void setContextSlot(HtmlComponent component, MetaSlotKey slotKey) {
			HtmlContainer container = (HtmlContainer) component;

			super.setContextSlot(container.getChildren().get(0), slotKey);
		}

		@Override
		public void applyStyle(HtmlComponent component) {
			HtmlContainer container = (HtmlContainer) component;

			super.applyStyle(container.getChildren().get(0));
		}

	}

}
