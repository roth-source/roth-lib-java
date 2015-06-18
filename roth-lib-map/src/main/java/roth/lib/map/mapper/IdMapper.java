package roth.lib.map.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class IdMapper<T extends Annotation> extends AnnotationMapper<T>
{
	
	public IdMapper(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public boolean hasId(Field field)
	{
		return getDeclaredAnnotation(field) != null;
	}
	
	public boolean isGeneratedFromField(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? isGenerated(annotation) : false;
	}
	
	public abstract boolean isGenerated(T annotation);
	
}
