package roth.lib.java.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class GetPropertyNameAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetPropertyNameAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getPropertyName(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? getPropertyName(annotation) : null;
	}
	
	public abstract String getPropertyName(T annotation);
	
}
