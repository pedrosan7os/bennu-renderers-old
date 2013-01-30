package pt.ist.bennu.renderers.core.validators;

import pt.ist.bennu.renderers.core.components.HtmlSimpleValueComponent;
import pt.ist.bennu.renderers.core.components.Validatable;

public class DegreeIdCardNameValidator extends HtmlValidator {

	public DegreeIdCardNameValidator() {
		super();
		setMessage("renderers.validator.degree.id.card.name");
	}

	public DegreeIdCardNameValidator(HtmlChainValidator htmlChainValidator) {
		super(htmlChainValidator);
		setMessage("renderers.validator.degree.id.card.name");
	}

	@Override
	public void performValidation() {
		final Validatable component = getComponent();
		if (component instanceof HtmlSimpleValueComponent) {
			final String value = component.getValue();
			setValid(value != null && value.length() > 0 && value.length() <= 42);
		} else {
			setValid(false);
		}
	}

}
