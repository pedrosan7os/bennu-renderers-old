package pt.ist.bennu.renderers.core.components;

import pt.ist.bennu.renderers.core.validators.HtmlChainValidator;
import pt.ist.bennu.renderers.core.validators.HtmlValidator;

public interface Validatable {
	public String getValue();

	public String[] getValues();

	public void setChainValidator(HtmlChainValidator htmlChainValidator);

	public void addValidator(HtmlValidator htmlValidator);

	public HtmlChainValidator getChainValidator();
}
