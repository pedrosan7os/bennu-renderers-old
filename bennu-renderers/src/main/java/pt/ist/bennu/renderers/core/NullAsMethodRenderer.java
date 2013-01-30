package pt.ist.bennu.renderers.core;

import java.util.Collection;

import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.schemas.Schema;
import pt.ist.bennu.renderers.core.utils.RenderKit;
import pt.ist.bennu.renderers.core.utils.RenderUtils;
import pt.ist.bennu.renderers.core.utils.RendererPropertyUtils;

/**
 * This renderer protects the presentation of the <code>null</code> value. If you need to present the values of some slots but the
 * holder object is null you can use this renderer to present the value of another method instead.
 * 
 * @author rcro
 */
public class NullAsMethodRenderer extends FormatRenderer {

	private String subLayout;
	private String subSchema;

	private String alternativeMethod;

	private boolean moduleRelative;
	private boolean contextRelative;

	public String getSubLayout() {
		return this.subLayout;
	}

	public String getAlternativeMethod() {
		return alternativeMethod;
	}

	/**
	 * The alternative method used to get the value if the original method returns null.
	 * 
	 * @property
	 */
	public void setAlternativeMethod(String alternativeMethod) {
		this.alternativeMethod = alternativeMethod;
	}

	/**
	 * The layout used to represent the object.
	 * 
	 * @property
	 */
	public void setSubLayout(String subLayout) {
		this.subLayout = subLayout;
	}

	public String getSubSchema() {
		return this.subSchema;
	}

	/**
	 * The schema used when representing the object.
	 * 
	 * @property
	 */
	public void setSubSchema(String subSchema) {
		this.subSchema = subSchema;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				Schema schema = RenderKit.getInstance().findSchema(getSubSchema());
				Object objectToRender = object;
				if (isEmpty(object) && getAlternativeMethod() != null) {
					objectToRender = RendererPropertyUtils.getProperty(getTargetObject(object), getAlternativeMethod(), false);
				}
				if (getFormat() == null) {
					return renderValue(objectToRender, type, schema, getSubLayout());
				} else {
					String formatedObject = RenderUtils.getFormattedProperties(getFormat(), objectToRender);
					return new HtmlText(formatedObject);
				}
			}

			private boolean isEmpty(Object object) {
				if (object == null || object.equals("")) {
					return true;
				}

				if (object instanceof Collection) {
					if (((Collection) object).isEmpty()) {
						return true;
					}
				}

				return false;
			}

		};
	}

	protected Object getTargetObject(Object object) {
		if (getContext().getParentContext() != null) {
			return getContext().getParentContext().getMetaObject().getObject();
		} else {
			return null;
		}
	}

	public boolean isContextRelative() {
		return contextRelative;
	}

	public void setContextRelative(boolean contextRelative) {
		this.contextRelative = contextRelative;
	}

	public boolean isModuleRelative() {
		return moduleRelative;
	}

	public void setModuleRelative(boolean moduleRelative) {
		this.moduleRelative = moduleRelative;
	}

}
