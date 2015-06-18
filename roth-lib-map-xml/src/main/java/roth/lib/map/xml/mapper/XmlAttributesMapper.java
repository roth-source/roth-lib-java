package roth.lib.map.xml.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import roth.lib.map.mapper.AnnotationMapper;

public abstract class XmlAttributesMapper<T extends Annotation> extends AnnotationMapper<T>
{
	
	public XmlAttributesMapper(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public boolean hasXmlAttributes(Field field)
	{
		return getDeclaredAnnotation(field) != null;
	}
	
}
