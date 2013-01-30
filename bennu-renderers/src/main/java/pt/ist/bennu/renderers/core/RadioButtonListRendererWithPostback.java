package pt.ist.bennu.renderers.core;

import org.apache.commons.collections.Predicate;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlHiddenField;
import pt.ist.bennu.renderers.core.components.HtmlInlineContainer;
import pt.ist.bennu.renderers.core.components.HtmlRadioButton;
import pt.ist.bennu.renderers.core.components.HtmlRadioButtonList;
import pt.ist.bennu.renderers.core.components.controllers.HtmlController;
import pt.ist.bennu.renderers.core.components.state.IViewState;
import pt.ist.bennu.renderers.core.components.state.ViewDestination;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.model.MetaSlot;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer can be used as the input for a list of objects. The list of objects the user can choose will be presented as an
 * html list were each list item will contain the presentation of the object and a radio button that allows to choose that
 * particular object. When submiting, the selected object will be the value passed to the slot.
 * 
 * <p>
 * Example: <form> <input type="radio" name="option"/> <em>&lt;object A presentation&gt;</em><br/>
 * <input type="radio" name="option"/><em>&lt;object B presentation&gt;</em><br/>
 * <input type="radio" name="option"/><em>&lt;object C presentation&gt;</em> </form>
 */
@Renderer(mode = RenderMode.INPUT, type = Object.class, layout = "radio-select-postback", properties = {
		@RendererProperty(name = "eachClasses", value = "dinline"),
		@RendererProperty(name = "style", value = "list-style: none; padding: 0; margin: 0;") })
public class RadioButtonListRendererWithPostback extends RadioButtonListRenderer {

	private final String HIDDEN_NAME = "postback";

	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new RadioButtonListLayoutWithPostback();
	}

	class RadioButtonListLayoutWithPostback extends RadioButtonListLayout {

		@Override
		public HtmlComponent createComponent(Object object, Class type) {
			HtmlInlineContainer container = new HtmlInlineContainer();

			String prefix =
					HtmlComponent.getValidIdOrName(((MetaSlot) getInputContext().getMetaObject()).getKey().toString()
							.replaceAll("\\.", "_").replaceAll("\\:", "_"));

			HtmlHiddenField hidden = new HtmlHiddenField(prefix + HIDDEN_NAME, "");

			final HtmlRadioButtonList htmlComponent = (HtmlRadioButtonList) super.createComponent(object, type);
			for (final HtmlRadioButton radioButton : htmlComponent.getRadioButtons()) {
				radioButton.setOnClick("this.form." + prefix + HIDDEN_NAME + ".value='true';this.form.submit();");
			}
			htmlComponent.setController(new PostBackController(hidden, destination));

			container.addChild(hidden);
			container.addChild(htmlComponent);

			return container;
		}

		@Override
		public void applyStyle(HtmlComponent component) {
			HtmlInlineContainer container = (HtmlInlineContainer) component;
			HtmlComponent list = container.getChild(new Predicate() {

				@Override
				public boolean evaluate(Object arg0) {
					return arg0 instanceof HtmlRadioButtonList;
				}

			});
			super.applyStyle(list);
		}
	}

	private static class PostBackController extends HtmlController {

		private final HtmlHiddenField hidden;

		private final String destination;

		public PostBackController(HtmlHiddenField hidden, String destination) {
			this.hidden = hidden;
			this.destination = destination;
		}

		@Override
		public void execute(IViewState viewState) {
			if (hidden.getValue() != null && hidden.getValue().equalsIgnoreCase("true")) {
				String destinationName = this.destination == null ? "postback" : this.destination;
				ViewDestination destination = viewState.getDestination(destinationName);

				if (destination != null) {
					viewState.setCurrentDestination(destination);
				} else {
					viewState.setCurrentDestination("postBack");
				}

				hidden.setValue("false");
				viewState.setSkipValidation(true);
			}

		}

	}

}
