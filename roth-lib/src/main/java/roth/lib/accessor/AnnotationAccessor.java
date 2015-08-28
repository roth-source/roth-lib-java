package roth.lib.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class AnnotationAccessor<T extends Annotation>
{
	protected Class<T> annotationClass;
	
	protected AnnotationAccessor(Class<T> annotationClass)
	{
		this.annotationClass = annotationClass;
	}
	
	public Class<T> getAnnotationClass()
	{
		return annotationClass;
	}
	
	public T getDeclaredAnnotation(Class<?> klass)
	{
		return klass != null ? klass.getDeclaredAnnotation(annotationClass) : null;
	}
	
	public T getDeclaredAnnotation(Field field)
	{
		return field != null ? field.getDeclaredAnnotation(annotationClass) : null;
	}
	
	protected static boolean isValid(String value)
	{
		return value != null & !value.isEmpty();
	}
	
}
