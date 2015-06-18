package roth.lib.map.xml.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import roth.lib.map.mapper.AnnotationMapper;

public abstract class XmlAttributeMapper<T extends Annotation> extends AnnotationMapper<T>
{
	
	public XmlAttributeMapper(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getXmlAttributeNameFromField(Field field)
	{
		String xmlAttributeName = null;
		T annotation = getDeclaredAnnotation(field);
		if(annotation != null)
		{
			xmlAttributeName = annotation != null ? getXmlAttributeName(field, annotation) : null;
		}
		return xmlAttributeName;
	}
	
	public abstract String getXmlAttributeName(Field field, T annotation);
	
}
