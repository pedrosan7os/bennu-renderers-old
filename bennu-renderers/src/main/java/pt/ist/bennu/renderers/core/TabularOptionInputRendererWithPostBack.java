package pt.ist.bennu.renderers.core;

import java.util.List;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlCheckBox;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlHiddenField;
import pt.ist.bennu.renderers.core.components.HtmlInlineContainer;
import pt.ist.bennu.renderers.core.components.HtmlSimpleValueComponent;
import pt.ist.bennu.renderers.core.components.controllers.HtmlController;
import pt.ist.bennu.renderers.core.components.state.IViewState;
import pt.ist.bennu.renderers.core.components.state.ViewDestination;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer allows you choose several object from a list of choices. The list of choices is presented in a table but each row
 * has a checkbox that allows you to select the object in that row.
 * 
 * <p>
 * The list of options is given by a {@link pt.ist.bennu.renderers.core.DataProvider data provider}.
 * 
 * <p>
 * Example:
 * <table border="1">
 * <thead>
 * <th></th>
 * <th>Name</th>
 * <th>Age</th>
 * <th>Gender</th>
 * </thead>
 * <tr>
 * <td><input type="checkbox"/></td>
 * <td>Name A</td>
 * <td>20</td>
 * <td>Female</td>
 * </tr>
 * <tr>
 * <td><input type="checkbox" checked="checked"/></td>
 * <td>Name B</td>
 * <td>22</td>
 * <td>Male</td>
 * </tr>
 * <tr>
 * <td><input type="checkbox" checked="checked"/></td>
 * <td>Name C</td>
 * <td>21</td>
 * <td>Female</td>
 * </tr>
 * </table>
 * 
 * @author pcma
 */
@Renderer(mode = RenderMode.INPUT, type = List.class, layout = "tabular-option-postback")
public class TabularOptionInputRendererWithPostBack extends TabularOptionInputRenderer {

	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	protected HtmlComponent renderComponent(Layout layout, Object object, Class type) {
		HtmlComponent component = super.renderComponent(layout, object, type);

		CheckableTabularLayout checkableLayout = (CheckableTabularLayout) layout;
		List<HtmlCheckBox> checkboxes = checkableLayout.getCheckBoxes();

		for (HtmlCheckBox checkbox : checkboxes) {
			checkbox.setOnClick("this.form.postback.value=1; this.form.submit();");
		}

		HtmlInlineContainer htmlInlineContainer = new HtmlInlineContainer();
		HtmlHiddenField hiddenField = new HtmlHiddenField();
		hiddenField.setName("postback");
		htmlInlineContainer.addChild(hiddenField);
		htmlInlineContainer.addChild(component);
		hiddenField.setController(new PostBackController(getDestination()));
		return htmlInlineContainer;
	}

	private static class PostBackController extends HtmlController {

		private final String destination;

		public PostBackController(String destination) {
			this.destination = destination;
		}

		@Override
		public void execute(IViewState viewState) {
			HtmlSimpleValueComponent component = (HtmlSimpleValueComponent) getControlledComponent();

			if (component.getValue() != null && component.getValue().length() > 0) {
				String destinationName = this.destination == null ? "postback" : this.destination;
				ViewDestination destination = viewState.getDestination(destinationName);

				if (destination != null) {
					viewState.setCurrentDestination(destination);
				} else {
					viewState.setCurrentDestination("postBack");
				}

				viewState.setSkipValidation(true);
			}

			component.setValue(null);
		}

	}

}
