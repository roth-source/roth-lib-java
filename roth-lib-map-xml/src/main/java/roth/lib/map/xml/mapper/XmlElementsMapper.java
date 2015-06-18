package roth.lib.map.xml.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import roth.lib.map.mapper.AnnotationMapper;

public abstract class XmlElementsMapper<T extends Annotation> extends AnnotationMapper<T>
{
	
	public XmlElementsMapper(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getXmlElementsNameFromField(Field field)
	{
		String xmlElementsName = null;
		T annotation = getDeclaredAnnotation(field);
		if(annotation != null)
		{
			xmlElementsName = annotation != null ? getXmlElementsName(field, annotation) : null;
		}
		return xmlElementsName;
	}
	
	public abstract String getXmlElementsName(Field field, T annotation);
	
}
