package pt.ist.bennu.renderers.actions;

import org.apache.struts.action.ActionForward;

public class DefaultContext implements Context {
	private String layout = "/renderers/layout.jsp";

	private String body;

	@Override
	public ActionForward forward(String body) {
		this.body = body;
		return new ActionForward(layout);
	}

	public String getBody() {
		return body;
	}
}
