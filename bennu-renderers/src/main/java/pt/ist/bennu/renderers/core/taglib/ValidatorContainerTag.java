package pt.ist.bennu.renderers.core.taglib;

public interface ValidatorContainerTag {

	public void addValidator(String validatorClassName);

	public void addValidatorProperty(String validatorClassName, String propertyName, String propertyValue);
}
