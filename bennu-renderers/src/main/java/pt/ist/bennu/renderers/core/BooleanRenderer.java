package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

/**
 * The default output renderer for a boolean value. The value is used to search for the corresponding message in the resources.
 * The key <tt>TRUE</tt> and <tt>FALSE</tt> are used to retrieve the messages for the <tt>true</tt> and <tt>false</tt> values.
 * 
 * @author cfgi
 */
@Renderers({ @Renderer(type = boolean.class), @Renderer(type = Boolean.class) })
public class BooleanRenderer extends OutputRenderer {

	private String trueLabel;
	private String falseLabel;
	private String nullLabel;
	private String bundle;

	public String getBundle() {
		return this.bundle;
	}

	/**
	 * Chooses the label to be displayed when it is null
	 * 
	 * @property
	 */
	public String getNullLabel() {
		return nullLabel;
	}

	public void setNullLabel(String nullLabel) {
		this.nullLabel = nullLabel;
	}

	/**
	 * Chooses the bundle in wich the labels will be searched.
	 * 
	 * @property
	 */
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getFalseLabel() {
		return this.falseLabel;
	}

	/**
	 * The label to be used when presenting a <code>false</code> value.
	 * 
	 * @property
	 */
	public void setFalseLabel(String falseLabel) {
		this.falseLabel = falseLabel;
	}

	public String getTrueLabel() {
		return this.trueLabel;
	}

	/**
	 * The label to be used when presenting the <code>true</code> value.
	 * 
	 * @property
	 */
	public void setTrueLabel(String trueLabel) {
		this.trueLabel = trueLabel;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				Boolean booleanValue = (Boolean) object;

				if (booleanValue == null) {
					String nullLabel = getNullLabel();
					return new HtmlText(nullLabel != null ? RenderUtils.getResourceString(getBundle(), nullLabel) : "");
				}

				String booleanResourceKey = getBooleanLabel(booleanValue);
				return new HtmlText(RenderUtils.getResourceString(getBundle(), booleanResourceKey));
			}

			private String getBooleanLabel(Boolean booleanValue) {
				String label = booleanValue ? getTrueLabel() : getFalseLabel();

				if (label != null) {
					return label;
				} else {
					return booleanValue.toString().toUpperCase();
				}
			}

		};
	}
}
