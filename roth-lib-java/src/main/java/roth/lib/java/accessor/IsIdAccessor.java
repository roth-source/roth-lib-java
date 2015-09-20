package roth.lib.java.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class IsIdAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public IsIdAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public boolean isId(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? isId(annotation) : false;
	}
	
	public boolean isId(T annotation)
	{
		return true;
	}
	
}
