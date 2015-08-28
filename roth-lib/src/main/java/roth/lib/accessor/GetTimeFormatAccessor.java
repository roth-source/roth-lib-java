package roth.lib.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class GetTimeFormatAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetTimeFormatAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getTimeFormat(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? getTimeFormat(annotation) : null;
	}
	
	public abstract String getTimeFormat(T annotation);
	
}
