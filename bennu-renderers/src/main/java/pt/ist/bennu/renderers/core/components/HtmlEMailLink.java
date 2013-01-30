package pt.ist.bennu.renderers.core.components;

public class HtmlEMailLink extends HtmlLink {

	public HtmlEMailLink(final String email) {
		setUrl("mailto:" + email);
		setText(email);
		setContextRelative(false);
		setModuleRelative(false);
	}

}
