package pt.ist.bennu.renderers.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pt.ist.bennu.renderers.core.utils.RenderMode;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Renderer {
	Class<?> type();

	RenderMode mode() default RenderMode.OUTPUT;

	String layout() default "";

	RendererProperty[] properties() default {};
}
