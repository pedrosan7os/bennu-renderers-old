package pt.ist.bennu.renderers.example;

import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.bennu.core.domain.Bennu;
import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.core.security.UserView;
import pt.ist.bennu.dispatch.Application;
import pt.ist.bennu.dispatch.Functionality;
import pt.ist.bennu.renderers.actions.ContextBaseAction;
import pt.ist.bennu.renderers.annotation.Mapping;

@Mapping(path = "/hello")
@Application(bundle = "resources.ExampleResources", path = "example", description = "title.renderers.example.hello.description", title = "title.renderers.example.hello")
public class HelloWorldAction extends ContextBaseAction {
	public static class HelloUser implements Serializable {
		private User user;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Set<User> getSystemUsers() {
			return Bennu.getInstance().getUsersSet();
		}
	}

	@Functionality(app = HelloWorldAction.class, path = "hello", bundle = "resources.ExampleResources", description = "title.renderers.example.hello.description", title = "title.renderers.example.hello")
	public ActionForward askname(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HelloUser hello = new HelloUser();
		hello.setUser(UserView.getUser());
		request.setAttribute("hello", hello);
		return forward(request, "/example/askname.jsp");
	}

	public ActionForward hello(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HelloUser hello = getRenderedObject("hello");
		request.setAttribute("hello", hello);
		return forward(request, "/example/hello.jsp");
	}
}
