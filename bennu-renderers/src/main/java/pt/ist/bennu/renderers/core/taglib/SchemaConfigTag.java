package pt.ist.bennu.renderers.core.taglib;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import pt.ist.bennu.renderers.core.schemas.Schema;
import pt.ist.bennu.renderers.core.schemas.SchemaSlotDescription;

public class SchemaConfigTag extends BodyTagSupport {

	private BaseRenderObjectTag parent = null;

	private String type;

	private String bundle;

	private List<SchemaSlotDescription> slots;

	public SchemaConfigTag() {
	}

	@Override
	public int doStartTag() throws JspException {
		this.slots = new ArrayList<SchemaSlotDescription>();
		this.parent = (BaseRenderObjectTag) findAncestorWithClass(this, BaseRenderObjectTag.class);

		if (this.parent == null) {
			throw new RuntimeException("layout tag can only be used inside a renderer tag");
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		Schema anonymousSchema = null;
		try {
			anonymousSchema = new Schema(null, Class.forName(getType()));
			for (SchemaSlotDescription slot : getSlots()) {
				anonymousSchema.addSlotDescription(slot);
			}

			this.parent.setAnonymousSchema(anonymousSchema);

			release();
			return EVAL_PAGE;
		} catch (ClassNotFoundException e) {
			throw new JspException(e);
		}
	}

	@Override
	public void release() {
		this.parent = null;
		this.slots = null;
		super.release();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SchemaSlotDescription> getSlots() {
		return slots;
	}

	public void addSlots(SchemaSlotDescription slot) {
		this.slots.add(slot);
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

}
