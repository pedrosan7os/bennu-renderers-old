package pt.ist.bennu.renderers.extensions;

import java.util.Collection;
import java.util.List;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.CheckBoxOptionListRenderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderMode;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

/**
 * This is the Fenix extension to the {@link pt.ist.bennu.renderers.core.CheckBoxOptionListRenderer}.
 * 
 * {@inheritDoc}
 * 
 * @author cfgi
 */
@Renderer(mode = RenderMode.INPUT, type = List.class, layout = "option-select", properties = { @RendererProperty(
		name = "eachClasses",
		value = "dinline") })
public class InputCheckBoxListRenderer extends CheckBoxOptionListRenderer {
	private String filterClass;

	private String emptyMessageKey;

	private String emptyMessageBundle;

	private String emptyMessageClasses;

	/**
	 * This property allows you to configure the css classes used in the empty message
	 * 
	 * @property
	 */

	public String getEmptyMessageClasses() {
		return emptyMessageClasses;
	}

	public void setEmptyMessageClasses(String emptyMessageClasses) {
		this.emptyMessageClasses = emptyMessageClasses;
	}

	/**
	 * This property allows you to configure the bundle that the empty message key uses
	 * 
	 * @property
	 */
	public String getEmptyMessageBundle() {
		return emptyMessageBundle;
	}

	public void setEmptyMessageBundle(String emptyMessageBundle) {
		this.emptyMessageBundle = emptyMessageBundle;
	}

	/**
	 * This property allows you to configure a display message in case the object list is empty
	 * 
	 * @property
	 */
	public String getEmptyMessageKey() {
		return emptyMessageKey;
	}

	public void setEmptyMessageKey(String emptyMessageKey) {
		this.emptyMessageKey = emptyMessageKey;
	}

	public String getFilterClass() {
		return this.filterClass;
	}

	/**
	 * Since all objects of a given type are selected with {@link #setChoiceType(String) choiceType}, this property allows you to
	 * specify a {@link DataFilter data filter} that filters objects that are not allowed from the collection created by the
	 * provider.
	 * 
	 * @property
	 */
	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}

	// HACK: duplicated code, id=inputChoices.selectPossibilitiesAndConverter
	@Override
	protected Converter getConverter() {
		return super.getConverter();
	}

	// HACK: duplicated code, id=inputChoices.selectPossibilitiesAndConverter
	@Override
	protected Collection getPossibleObjects() {
		return super.getPossibleObjects();

	}

	@Override
	public Layout getLayout(Object object, Class type) {
		return new InputCheckBoxLayoutWithEmptyMessage();
	}

	class InputCheckBoxLayoutWithEmptyMessage extends CheckBoxListLayout {

		private boolean empty;

		@Override
		public HtmlComponent createComponent(Object object, Class type) {
			Collection collection = (Collection) object;
			HtmlComponent component;

			if (getEmptyMessageKey() != null && collection.isEmpty() && getPossibleObjects().isEmpty()) {
				component = new HtmlText(RenderUtils.getResourceString(getEmptyMessageBundle(), getEmptyMessageKey()));
				this.empty = true;
			} else {
				component = super.createComponent(object, type);
				this.empty = false;
			}
			return component;
		}

		@Override
		public void applyStyle(HtmlComponent component) {
			if (this.empty) {
				component.setClasses(getEmptyMessageClasses());
			} else {
				super.applyStyle(component);
			}

		}
	}
}