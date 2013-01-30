package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlImage;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

/**
 * The graphic output renderer for a boolean value. The value is used to apply the defined icon for such boolean value.
 * 
 * @author djfsi
 */
@Renderer(type = Boolean.class, layout = "boolean-icon", properties = {
		@RendererProperty(name = "trueIconPath", value = "/images/ok.jpg"),
		@RendererProperty(name = "falseIconPath", value = "/images/cancel.jpg"),
		@RendererProperty(name = "contextRelative", value = "true") })
public class BooleanImageRenderer extends OutputRenderer {

	private String trueIconPath;
	private String falseIconPath;
	private Boolean contextRelative;
	private Boolean nullAsFalse;

	public String getFalseIconPath() {
		return this.falseIconPath;
	}

	/**
	 * The icon to be shown when presenting a <code>false</code> value.
	 * 
	 * @property
	 */
	public void setFalseIconPath(String falseIconPath) {
		this.falseIconPath = falseIconPath;
	}

	public String getTrueIconPath() {
		return this.trueIconPath;
	}

	/**
	 * The icon to be shown when presenting the <code>true</code> value.
	 * 
	 * @property
	 */
	public void setTrueIconPath(String trueIconPath) {
		this.trueIconPath = trueIconPath;
	}

	public Boolean isContextRelative() {
		return this.contextRelative;
	}

	/**
	 * This identifies the nature of the icon file path as being either context relative or not.
	 * 
	 * @property
	 */
	public void setContextRelative(Boolean contextRelative) {
		this.contextRelative = contextRelative;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				Boolean booleanValue = (Boolean) object;

				if (booleanValue == null) {
					if (!isNullAsFalse()) {
						return new HtmlText();
					}
					booleanValue = false;
				}

				StringBuilder pathBuilder = new StringBuilder();
				if (contextRelative) {
					pathBuilder.append(RenderUtils.getContextRelativePath(""));
				}

				pathBuilder.append(getIconPath(booleanValue));
				String fullPath = pathBuilder.toString();

				HtmlImage img = new HtmlImage();
				img.setSource(fullPath);

				return img;
			}

			private String getIconPath(Boolean booleanValue) {
				return booleanValue ? getTrueIconPath() : getFalseIconPath();
			}

		};
	}

	public void setNullAsFalse(Boolean nullAsFalse) {
		this.nullAsFalse = nullAsFalse;
	}

	public Boolean isNullAsFalse() {
		return nullAsFalse;
	}
}
