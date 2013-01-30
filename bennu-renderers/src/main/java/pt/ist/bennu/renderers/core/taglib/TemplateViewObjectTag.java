package pt.ist.bennu.renderers.core.taglib;

import javax.servlet.jsp.JspException;

import pt.ist.bennu.renderers.core.components.Constants;
import pt.ist.bennu.renderers.core.model.MetaObject;

public class TemplateViewObjectTag extends ViewObjectTag {

	public TemplateViewObjectTag() {
		super();
	}

	@Override
	public String getName() {
		if (super.getName() == null) {
			return Constants.TEMPLATE_OBJECT_NAME;
		} else {
			return super.getName();
		}
	}

	@Override
	protected Object getTargetObjectByName() throws JspException {
		MetaObject metaObject = (MetaObject) super.getTargetObjectByName();

		return metaObject.getObject();
	}

	@Override
	public String getScope() {
		if (super.getName() == null && super.getScope() == null) {
			return "request";
		} else {
			return super.getScope();
		}
	}
}
