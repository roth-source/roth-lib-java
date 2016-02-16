package roth.lib.java.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD}) 
@Retention(RUNTIME)
public @interface Property
{
	// GENERAL
	String name();
	String timeFormat() default "";
	String[] exclude() default {};
	
	// DB
	boolean db() default true;
	String dbName() default "";
	boolean id() default false;
	boolean generated() default false;
	
	// MYSQL
	boolean mysql() default true;
	String mysqlName() default "";
	
	// SERIAL
	boolean serial() default true;
	String serialName() default "";
	
	// JSON
	boolean json() default true;
	String jsonName() default "";
	Class<?> jsonSerializer() default Void.class;
	Class<?> jsonDeserializer() default Void.class;
	
	// XML
	boolean xml() default true;
	String xmlName() default "";
	boolean attribute() default false;
	String elementsName() default "";
	Class<?> xmlSerializer() default Void.class;
	Class<?> xmlDeserializer() default Void.class;
	
	// FORM
	boolean form() default true;
	String formName() default "";
	Class<?> formSerializer() default Void.class;
	Class<?> formDeserializer() default Void.class;
	
	// TABLE
	boolean table() default true;
	String tableName() default "";
	Class<?> tableSerializer() default Void.class;
	Class<?> tableDeserializer() default Void.class;
	
	// VALIDATION
	boolean required() default false;
	String[] filter() default {};
	String[] validate() default {};
	
}
