package roth.lib.accessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class GetExcludesAccessor<T extends Annotation> extends AnnotationAccessor<T>
{
	
	public GetExcludesAccessor(Class<T> annotationClass)
	{
		super(annotationClass);
	}
	
	public LinkedList<String> getExcludes(Field field)
	{
		T annotation = getDeclaredAnnotation(field);
		return annotation != null ? new LinkedList<String>(Arrays.asList(getExcludes(annotation))) : null;
	}
	
	public abstract String[] getExcludes(T annotation);
	
}
