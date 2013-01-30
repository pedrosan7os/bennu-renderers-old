package pt.ist.bennu.renderers.extensions.validators;

import pt.ist.bennu.renderers.core.validators.EmailValidator;
import pt.ist.bennu.renderers.core.validators.HtmlChainValidator;

public class RequiredEmailValidator extends EmailValidator {

	public RequiredEmailValidator() {
		super();
	}

	public RequiredEmailValidator(HtmlChainValidator htmlChainValidator) {
		super(htmlChainValidator);
	}

	@Override
	public void performValidation() {
		final String email = getComponent().getValue();
		if (email != null && email.length() > 0) {
			super.performValidation();
		} else {
			setValid(false);
			setMessage("renderers.validator.required");
		}
	}

}
