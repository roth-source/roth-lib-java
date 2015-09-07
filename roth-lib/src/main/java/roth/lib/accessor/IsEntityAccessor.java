package roth.lib.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import roth.lib.util.ReflectionUtil;

public abstract class IsEntityAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public IsEntityAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public boolean isEntity(Type type)
	{
		T annotation = getDeclaredAnnotation(ReflectionUtil.getTypeClass(type));
		return annotation != null ? isEntity(annotation) : false;
	}
	
	public boolean isEntity(T annotation)
	{
		return true;
	}
	
}
