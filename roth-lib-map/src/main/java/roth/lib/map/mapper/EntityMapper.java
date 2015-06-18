package roth.lib.map.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import roth.lib.map.util.MapperUtil;

public abstract class EntityMapper<T extends Annotation> extends AnnotationMapper<T>
{
	
	public EntityMapper(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getEntityNameFromClass(Type type)
	{
		String entityName = null;
		Class<?> klass = MapperUtil.getClass(type);
		T annotation = getDeclaredAnnotation(klass);
		if(annotation != null)
		{
			entityName = annotation != null ? getEntityName(annotation) : null;
			entityName = entityName != null && !entityName.isEmpty() ? entityName : klass.getSimpleName();
		}
		return entityName;
	}
	
	public abstract String getEntityName(T annotation);
	
}
