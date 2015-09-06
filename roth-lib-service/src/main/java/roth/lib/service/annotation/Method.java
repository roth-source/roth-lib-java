package roth.lib.service.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(METHOD)
@Retention(RUNTIME)
public @interface Method
{
	String name();
	boolean ajax();
	boolean api();
	boolean authenticated();
	boolean post() default true;
	boolean get() default false;
	boolean put() default false;
	boolean delete() default false;
	boolean gzippedInput() default false;
	
}
