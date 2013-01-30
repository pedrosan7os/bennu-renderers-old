package pt.ist.bennu.renderers.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

	String path();

	String formBean() default "";

	String attribute() default "";

	Class<? extends ActionForm> formBeanClass() default ActionForm.class;

	String module() default "";

	String scope() default "request";

	boolean validate() default true;

	String parameter() default "method";

	Class<? extends ActionMapping> customMappingClass() default ActionMapping.class;

	String[] customMappingProperties() default {};

}
