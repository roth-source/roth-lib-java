package roth.lib.net.http.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;

import roth.lib.map.util.MapperUtil;
import roth.lib.net.http.service.HttpService;
import roth.lib.net.http.service.HttpServiceOld;

public class ServiceUtil
{
	
	protected ServiceUtil()
	{
		
	}
	
	public static LinkedList<Method> getMethods(Type type)
	{
		if(type == null)
		{
			return new LinkedList<Method>();
		}
		else
		{
			Class<?> klass = MapperUtil.getClass(type);
			if(HttpServiceOld.class.equals(klass))
			{
				return new LinkedList<Method>();
			}
			else
			{
				LinkedList<Method> methods = getMethods(klass.getSuperclass());
				for(Method method : Arrays.asList(klass.getDeclaredMethods()))
				{
					if(!Modifier.isStatic(method.getModifiers()))
					{
						methods.add(method);
					}
				}
				return methods;
			}
		}
	}
	
	public static LinkedList<Field> getFields(Type type)
	{
		if(type == null)
		{
			return new LinkedList<Field>();
		}
		else
		{
			Class<?> klass = MapperUtil.getClass(type);
			LinkedList<Field> fields = getFields(klass.getSuperclass());
			for(Field field : Arrays.asList(klass.getDeclaredFields()))
			{
				if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
				{
					fields.add(field);
				}
			}
			return fields;
		}
	}
	
	public static boolean hasAnnotation(HttpService service, Method method, Class<? extends Annotation> annotationClass)
	{
		return getAnnotation(service, method, annotationClass) != null;
	}
	
	public static <T extends Annotation> T getAnnotation(HttpService service, Method method, Class<T> annotationClass)
	{
		T annotation = method.getDeclaredAnnotation(annotationClass);
		if(annotation == null)
		{
			annotation = service.getClass().getDeclaredAnnotation(annotationClass);
		}
		return annotation;
	}
	
}
