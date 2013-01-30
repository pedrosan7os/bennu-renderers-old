package pt.ist.bennu.renderers.core.components;

public class HtmlHiddenField extends HtmlTextInput {

	public HtmlHiddenField() {
		super("hidden");
	}

	public HtmlHiddenField(String name, String value) {
		this();

		setName(name);
		setValue(value);
	}

}
