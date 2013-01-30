package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlPasswordInput;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer provides a standard way of doing the input of a password. The password is read with a password input field.
 * 
 * <p>
 * Example: <input type="password" value="the password"/>
 * 
 * @author naat
 */
@Renderer(mode = RenderMode.INPUT, layout = "password", type = String.class)
public class PasswordInputRenderer extends TextFieldRenderer {

	@Override
	protected HtmlComponent createTextField(Object object, Class type) {
		String string = (String) object;

		HtmlPasswordInput inputPassword = new HtmlPasswordInput();
		inputPassword.setValue(string);

		return inputPassword;
	}

}
