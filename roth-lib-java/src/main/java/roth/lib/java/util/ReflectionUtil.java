package roth.lib.java.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ReflectionUtil
{
	
	protected ReflectionUtil()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getTypeClass(Type type)
	{
		Class<T> klass = null;
		if(type instanceof Class<?>)
		{
			klass = (Class<T>) type;
		}
		else if(type instanceof ParameterizedType)
		{
			klass = (Class<T>) ((ParameterizedType) type).getRawType();
		}
		else if(type instanceof GenericArrayType)
		{
			klass = (Class<T>) Array.newInstance(getTypeClass(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
		}
		return klass;
	}
	
	public static Type getGenericType(Type type, Type fieldType)
	{
		if(type instanceof ParameterizedType)
		{
			Class<?> klass = getTypeClass(type);
			if(fieldType instanceof TypeVariable)
			{
				TypeVariable<?>[] typeVariables = klass.getTypeParameters();
				for(int i = 0; i < typeVariables.length; i++)
				{
					TypeVariable<?> typeVariable = typeVariables[i];
					if(typeVariable.equals(fieldType))
					{
						fieldType = ((ParameterizedType) type).getActualTypeArguments()[i];
						break;
					}
				}
			}
			else if(fieldType instanceof ParameterizedType)
			{
				Type[] typeArguements = ((ParameterizedType) fieldType).getActualTypeArguments();
				int hashCode = Arrays.hashCode(typeArguements);
				for(int i = 0; i < typeArguements.length; i ++)
				{
					typeArguements[i] = getGenericType(type, typeArguements[i]);
				}
				if(hashCode != Arrays.hashCode(typeArguements))
				{
					try
					{
						Field field = fieldType.getClass().getDeclaredField("actualTypeArguments");
						field.setAccessible(true);
						field.set(fieldType, typeArguements);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return fieldType;
	}
	
	public static LinkedList<Method> getMethods(Type type, Class<?> stopClass)
	{
		if(type == null)
		{
			return new LinkedList<Method>();
		}
		else
		{
			Class<?> klass = getTypeClass(type);
			if(stopClass.equals(klass))
			{
				return new LinkedList<Method>();
			}
			else
			{
				LinkedList<Method> methods = getMethods(klass.getSuperclass(), stopClass);
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
			Class<?> klass = getTypeClass(type);
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
	
	public static Object getFieldValue(Field field, Object object)
	{
		Object value = null;
		try
		{
			value = field.get(object);
		}
		catch(IllegalAccessException e)
		{
			
		}
		return value;
	}
	
	public static boolean isArray(Type type)
	{
		Class<?> klass = getTypeClass(type);
		return klass.isArray();
	}
	
	public static boolean isCollection(Type type)
	{
		Class<?> klass = getTypeClass(type);
		return Collection.class.isAssignableFrom(klass);
	}
	
	public static boolean isMap(Type type)
	{
		Class<?> klass = getTypeClass(type);
		return Map.class.isAssignableFrom(klass);
	}
	
	public static Class<?> getElementClass(Type type)
	{
		Class<?> elementClass = Object.class;
		Class<?> klass = getTypeClass(type);
		if(isArray(klass))
		{
			elementClass = klass.getComponentType();
		}
		else if(isCollection(klass))
		{
			if(type instanceof ParameterizedType)
			{
				elementClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
			}
		}
		else if(isMap(klass))
		{
			if(type instanceof ParameterizedType)
			{
				elementClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[1];
			}
		}
		return elementClass;
	}
	
	public static LinkedList<?> asCollection(Object value)
	{
		return (value instanceof Collection) ? new LinkedList<Object>((Collection<?>) value) : new LinkedList<Object>(Arrays.asList((Object[]) value));
	}
	
	public static LinkedHashMap<?, ?> asMap(Object value)
	{
		return (value instanceof Map) ? new LinkedHashMap<Object, Object>((Map<?, ?>) value) : new LinkedHashMap<Object, Object>();
	}
	
	public static <T extends Annotation> boolean hasAnnotation(Class<?> klass, Method method, Class<T> annotationClass)
	{
		return getAnnotation(klass, method, annotationClass) != null;
	}
	
	public static <T extends Annotation> T getAnnotation(Class<?> klass, Method method, Class<T> annotationClass)
	{
		T annotation = method.getDeclaredAnnotation(annotationClass);
		if(annotation == null)
		{
			annotation = klass.getDeclaredAnnotation(annotationClass);
		}
		return annotation;
	}
	
}
