package pt.ist.bennu.renderers.core.components;

public class HtmlSubmitButton extends HtmlInputButton {

	public HtmlSubmitButton() {
		super("submit");
	}

	public HtmlSubmitButton(String text) {
		this();

		setText(text);
	}
}
