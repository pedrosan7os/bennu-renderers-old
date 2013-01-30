package pt.ist.bennu.renderers.core.validators;

import pt.ist.bennu.renderers.core.utils.RenderUtils;
import pt.ist.bennu.renderers.util.PhoneUtil;

public class PhoneValidator extends HtmlValidator {

	public PhoneValidator() {
		super();
	}

	public PhoneValidator(HtmlChainValidator htmlChainValidator) {
		super(htmlChainValidator);
	}

	@Override
	public void performValidation() {
		setValid(PhoneUtil.isValidNumber(getComponent().getValue()));
	}

	@Override
	public String getErrorMessage() {
		return RenderUtils.getResourceString("renderers.validator.phone.number");
	}

}
