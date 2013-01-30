package pt.ist.bennu.renderers.core.validators;

import pt.ist.bennu.renderers.util.EMail;

public class EmailValidator extends RegexpValidator {

	public EmailValidator() {
		super(EMail.W3C_EMAIL_SINTAX_VALIDATOR);
		setMessage("renderers.validator.email");
	}

	public EmailValidator(HtmlChainValidator htmlChainValidator) {
		super(htmlChainValidator, EMail.W3C_EMAIL_SINTAX_VALIDATOR);

		setMessage("renderers.validator.email");
	}
}
