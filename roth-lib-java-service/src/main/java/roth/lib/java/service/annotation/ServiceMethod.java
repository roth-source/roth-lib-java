package roth.lib.java.service.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(METHOD)
@Retention(RUNTIME)
public @interface ServiceMethod
{
	String name() 				default "";
	String context() 			default "";
	boolean ajax() 				default true;
	boolean api() 				default false;
	boolean authenticated() 	default true;
	boolean post() 				default true;
	boolean get() 				default false;
	boolean put() 				default false;
	boolean delete() 			default false;
	boolean gzippedInput() 		default false;
	boolean prettyPrint() 		default false;
	boolean debugRequest() 		default true;
	boolean debugResponse() 	default true;
	
}
