package roth.lib.map.xml.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import roth.lib.accessor.AnnotationAccessor;

public abstract class GetXmlElementsNameAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetXmlElementsNameAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getXmlElementsName(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? getXmlElementsName(annotation) : null;
	}
	
	public abstract String getXmlElementsName(T annotation);
	
}
