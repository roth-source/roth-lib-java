package roth.lib.map.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class MapperUtil
{
	
	protected MapperUtil()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClass(Type type)
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
			klass = (Class<T>) Array.newInstance(getClass(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
		}
		return klass;
	}
	
	public static Type getGenericType(Type type, Type fieldType)
	{
		if(type instanceof ParameterizedType)
		{
			Class<?> klass = getClass(type);
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
	
}
