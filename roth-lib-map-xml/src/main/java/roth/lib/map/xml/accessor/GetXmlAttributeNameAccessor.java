package roth.lib.map.xml.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import roth.lib.accessor.AnnotationAccessor;

public abstract class GetXmlAttributeNameAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetXmlAttributeNameAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getXmlAttributeName(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? getXmlAttributeName(annotation) : null;
	}
	
	public abstract String getXmlAttributeName(T annotation);
	
}
