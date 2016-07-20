package roth.lib.java.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.deserializer.VoidDeserializer;
import roth.lib.java.filter.Filterer;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.serializer.VoidSerializer;
import roth.lib.java.time.TimeZone;
import roth.lib.java.validate.Validator;

@Target({FIELD}) 
@Retention(RUNTIME)
public @interface Property
{
	// GENERAL
	String name();
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
	TimeZone timeZone() default TimeZone.DEFAULT;
	String timeFormat() default "";
	Class<? extends Filterer>[] filter() default {};
	Class<? extends Deserializer<?>> deserializer() default VoidDeserializer.class;
	Class<? extends Serializer<?>> serializer() default VoidSerializer.class;
	
	// JSON
	boolean json() default true;
	String jsonName() default "";
	String jsonTimeFormat() default "";
	Class<? extends Filterer>[] jsonFilter() default {};
	Class<? extends Deserializer<?>> jsonDeserializer() default VoidDeserializer.class;
	Class<? extends Serializer<?>> jsonSerializer() default VoidSerializer.class;
	
	// XML
	boolean xml() default true;
	String xmlName() default "";
	boolean attribute() default false;
	String elementsName() default "";
	String xmlTimeFormat() default "";
	Class<? extends Filterer>[] xmlFilter() default {};
	Class<? extends Deserializer<?>> xmlDeserializer() default VoidDeserializer.class;
	Class<? extends Serializer<?>> xmlSerializer() default VoidSerializer.class;
	
	// FORM
	boolean form() default true;
	String formName() default "";
	String formTimeFormat() default "";
	Class<? extends Filterer>[] formFilter() default {};
	Class<? extends Deserializer<?>> formDeserializer() default VoidDeserializer.class;
	Class<? extends Serializer<?>> formSerializer() default VoidSerializer.class;
	
	// TABLE
	boolean table() default true;
	String tableName() default "";
	String tableTimeFormat() default "";
	Class<? extends Filterer>[] tableFilter() default {};
	Class<? extends Deserializer<?>> tableDeserializer() default VoidDeserializer.class;
	Class<? extends Serializer<?>> tableSerializer() default VoidSerializer.class;
	
	// VALIDATION
	boolean required() default false;
	Class<? extends Validator>[] validate() default {};
	int trimLength() default 0;
	int maxLength() default 0;
	
}
