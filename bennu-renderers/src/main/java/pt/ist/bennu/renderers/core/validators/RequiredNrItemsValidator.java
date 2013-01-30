package pt.ist.bennu.renderers.core.validators;

import pt.ist.bennu.renderers.core.components.HtmlCheckBoxList;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

public class RequiredNrItemsValidator extends HtmlValidator {
	private Integer nrRequiredItems;

	public RequiredNrItemsValidator() {
		super();
	}

	public RequiredNrItemsValidator(HtmlChainValidator htmlChainValidator) {
		super(htmlChainValidator);
	}

	@Override
	public void performValidation() {
		HtmlCheckBoxList component = (HtmlCheckBoxList) getComponent();

		String values[] = component.getValues();
		defineMessage();

		setValid(values.length >= getNrRequiredItems().intValue());

	}

	private void defineMessage() {
		setKey(false);
		if (getNrRequiredItems() == null) {
			throw new RuntimeException("renderers.validator.nr.items.not.specified");
		} else {
			setMessage(RenderUtils.getFormatedResourceString(getBundle(), "renderers.validator.invalid.nrItems",
					getNrRequiredItems()));
		}
	}

	public void setNrRequiredItems(Integer nrRequiredItems) {
		this.nrRequiredItems = nrRequiredItems;
	}

	public Integer getNrRequiredItems() {
		return nrRequiredItems;
	}

}
