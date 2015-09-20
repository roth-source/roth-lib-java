package roth.lib.java.map.xml.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import roth.lib.java.accessor.AnnotationAccessor;

public abstract class IsXmlAttributesAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public IsXmlAttributesAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public boolean isXmlAttributes(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? isXmlAttributes(annotation) : false;
	}
	
	public boolean isXmlAttributes(T annotation)
	{
		return true;
	}
	
}
