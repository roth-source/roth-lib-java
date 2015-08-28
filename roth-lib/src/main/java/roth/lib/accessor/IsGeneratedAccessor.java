package roth.lib.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class IsGeneratedAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public IsGeneratedAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public boolean isGenerated(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? isGenerated(annotation) : false;
	}
	
	public boolean isGenerated(T annotation)
	{
		return true;
	}
	
}
