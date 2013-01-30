package pt.ist.bennu.renderers.actions;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.bennu.core.util.Language;

public abstract class ContextBaseAction extends BaseAction {
	public static final String CONTEXT = "_CONTEXT_";

	public static final String LOCALE_BEAN = "localeBean";

	public static class LocaleBean implements Serializable {
		private static final long serialVersionUID = -4880869280578139842L;

		private Language language;

		public LocaleBean() {
			language = Language.getLanguage();
		}

		public Language getLanguage() {
			return language;
		}

		public void setLanguage(Language language) {
			this.language = language;
		}
	}

	@Override
	public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		request.setAttribute(LOCALE_BEAN, new LocaleBean());
		return super.execute(mapping, form, request, response);
	}

	public static ActionForward forward(final HttpServletRequest request, final String forward) {
		DefaultContext context = new DefaultContext();
		request.setAttribute(CONTEXT, context);
		return context.forward(forward);
	}
}
