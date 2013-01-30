package pt.ist.bennu.renderers.extensions.taglib;

import javax.servlet.jsp.JspException;

import pt.ist.bennu.renderers.core.taglib.ViewObjectTag;
import pt.ist.fenixframework.pstm.Transaction;

public class FenixViewObjectTag extends ViewObjectTag {
	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	@Override
	protected Object getTargetObject() throws JspException {
		Object object = super.getTargetObject();

		if (object == null) {
			object = getPersistentObject();

			if (object != null) {
				return super.getTargetObjectByProperty(object);
			}
		}

		return object;
	}

	protected Object getPersistentObject() throws JspException {
		if (getOid() != null) {
			final long oid = Long.parseLong(getOid());
			return Transaction.getObjectForOID(oid);
		}
		return null;
	}

	@Override
	public void release() {
		super.release();

		this.oid = null;
	}
}
