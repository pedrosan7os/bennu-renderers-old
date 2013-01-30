package pt.ist.bennu.renderers.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.bennu.core.domain.exceptions.DomainException;
import pt.ist.bennu.core.security.Authenticate;
import pt.ist.bennu.core.util.ConfigurationManager;
import pt.ist.bennu.core.util.ConfigurationManager.CasConfig;
import pt.ist.bennu.dispatch.Application;
import pt.ist.bennu.dispatch.Functionality;
import pt.ist.bennu.renderers.annotation.Mapping;

@Mapping(path = "/authentication")
@Application(
		path = "authenticate",
		group = "anyone",
		bundle = "resources.RendererResources",
		title = "title.renderers.authentication",
		description = "title.renderers.authentication.description")
public class AuthenticationAction extends ContextBaseAction {
	@Functionality(
			app = AuthenticationAction.class,
			path = "login",
			group = "anyone",
			bundle = "resources.RendererResources",
			title = "title.renderers.authentication.login",
			description = "title.renderers.authentication.login.description")
	public final ActionForward login(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException {
		final String username = getAttribute(request, "username");
		final String password = getAttribute(request, "password");
		CasConfig casConfig = ConfigurationManager.getCasConfig(request.getServerName());
		if (casConfig == null || !casConfig.isCasEnabled()) {
			try {
				Authenticate.login(request.getSession(), username.trim(), password, true);
				return new ActionForward("/", true);
			} catch (final DomainException e) {
				Authenticate.logout(request.getSession());
				request.setAttribute("authentication.failed", username);
				return new ActionForward("/");
			}
		}
		throw new ServletException("Attempted local login with CAS authentication enabled");
	}

	@Functionality(
			app = AuthenticationAction.class,
			path = "logout",
			group = "anyone",
			bundle = "resources.RendererResources",
			title = "title.renderers.authentication.logout",
			description = "title.renderers.authentication.logout.description")
	public final ActionForward logout(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) {
		Authenticate.logout(request.getSession());
		CasConfig config = ConfigurationManager.getCasConfig(request.getServerName());
		if (config != null && config.isCasEnabled()) {
			return new ActionForward(config.getCasLogoutUrl(), true);
		}
		return new ActionForward("/");
	}
}
