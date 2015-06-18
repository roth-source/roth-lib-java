package roth.lib.map.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class PropertyMapper<T extends Annotation> extends AnnotationMapper<T>
{
	
	public PropertyMapper(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getPropertyNameFromField(Field field)
	{
		String propertyName = null;
		T annotation = getDeclaredAnnotation(field);
		if(annotation != null)
		{
			propertyName = annotation != null ? getPropertyName(field, annotation) : null;
		}
		return propertyName;
	}
	
	public abstract String getPropertyName(Field field, T annotation);
	
	public Boolean isEntityNameFromField(Field field)
	{
		Boolean entityName = null;
		T annotation = getDeclaredAnnotation(field);
		if(annotation != null)
		{
			entityName = isEntityName(field, annotation);
		}
		return entityName;
	}
	
	public abstract boolean isEntityName(Field field, T annotation);
	
}
