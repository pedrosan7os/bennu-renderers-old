package pt.ist.bennu.renderers.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.bennu.dispatch.RenderersAnnotationProcessor;
import pt.ist.bennu.renderers.annotation.Mapping;

@Mapping(path = "/render")
public class RenderAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String function = request.getParameter("function");
		return new ActionForward(RenderersAnnotationProcessor.resolveForward(function));
	}
}
