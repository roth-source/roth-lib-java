package roth.lib.java.map.xml.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import roth.lib.java.accessor.AnnotationAccessor;
import roth.lib.java.util.ReflectionUtil;

public abstract class GetXmlPropertyNameAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetXmlPropertyNameAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public String getXmlPropertyName(Type type)
	{
		T annotation = getDeclaredAnnotation(ReflectionUtil.getTypeClass(type));
		return annotation != null ? getXmlPropertyName(annotation) : null;
	}
	
	public abstract String getXmlPropertyName(T annotation);

}
