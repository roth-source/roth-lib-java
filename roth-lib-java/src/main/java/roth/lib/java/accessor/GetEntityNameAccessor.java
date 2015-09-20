package roth.lib.java.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import roth.lib.java.util.ReflectionUtil;

public abstract class GetEntityNameAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetEntityNameAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getEntityName(Type type)
	{
		T annotation = getDeclaredAnnotation(ReflectionUtil.getTypeClass(type));
		return annotation != null ? getEntityName(annotation) : null;
	}
	
	public abstract String getEntityName(T annotation);
	
}
