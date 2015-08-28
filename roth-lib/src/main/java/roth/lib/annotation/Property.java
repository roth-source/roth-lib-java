package roth.lib.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD}) 
@Retention(RUNTIME)
public @interface Property
{
	String name();
	boolean form() default true;
	String formName() default "";
	boolean json() default true;
	String jsonName() default "";
	boolean xml() default true;
	String xmlName() default "";
	boolean db() default true;
	String dbName() default "";
	String timeFormat() default "";
	String[] exclude() default {};
	
}
