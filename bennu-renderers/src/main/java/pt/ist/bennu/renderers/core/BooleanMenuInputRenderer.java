package pt.ist.bennu.renderers.core;

import org.apache.commons.lang.StringUtils;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlMenu;
import pt.ist.bennu.renderers.core.components.HtmlMenuOption;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.model.MetaSlotKey;
import pt.ist.bennu.renderers.core.utils.RenderMode;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

/**
 * This renderer provides an alternative way of doing the input of a boolean value. This renderer presents an html menu with two
 * options: one for the <tt>true</tt> value, and other for the <tt>false</tt> value.
 * 
 * <p>
 * The options text is retrieved from the resources using the keys <tt>TRUE</tt> and <tt>FALSE</tt>.
 * 
 * <p>
 * Example: <select> <option>Yes</option> <option>No</option> </select>
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(mode = RenderMode.INPUT, layout = "menu", type = boolean.class),
		@Renderer(mode = RenderMode.INPUT, layout = "menu", type = Boolean.class) })
public class BooleanMenuInputRenderer extends InputRenderer {

	private String nullOptionKey;
	private String bundle;

	public String getNullOptionKey() {
		return nullOptionKey;
	}

	public void setNullOptionKey(String nullOptionKey) {
		this.nullOptionKey = nullOptionKey;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				HtmlMenu menu = new HtmlMenu();

				if (getNullOptionKey() != null) {
					String defaultOptionTitle = RenderUtils.getResourceString(getBundle(), getNullOptionKey());
					menu.createDefaultOption(defaultOptionTitle).setSelected(object == null);
					menu.setConverter(new Converter() {
						@Override
						public Object convert(Class type, Object value) {
							return StringUtils.isEmpty((String) value) ? null : Boolean.valueOf((String) value);
						}
					});
				}

				HtmlMenuOption trueOption = menu.createOption(RenderUtils.getResourceString("TRUE"));
				HtmlMenuOption falseOption = menu.createOption(RenderUtils.getResourceString("FALSE"));

				trueOption.setValue("true");
				falseOption.setValue("false");

				if (object != null) {
					((Boolean) object ? trueOption : falseOption).setSelected(true);
				}

				menu.setTargetSlot((MetaSlotKey) getInputContext().getMetaObject().getKey());

				return menu;
			}

		};
	}

}
