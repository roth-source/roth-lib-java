package roth.lib.java.util;

import java.lang.reflect.Method;

public class EnumUtil
{
	
	protected EnumUtil()
	{
		
	}
	
	public static String toString(Enum<?> value)
	{
		return value != null ? value.toString() : null;
	}
	
	public static <T extends Enum<?>> T fromString(String name, Class<T> klass)
	{
		T value = fromString(name, klass, "fromString");
		if(value == null)
		{
			value = fromString(name, klass, "valueOf");
		}
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?>> T fromString(String name, Class<T> klass, String methodName)
	{
		T value = null;
		try
		{
			Method method = klass.getMethod(methodName, String.class);
			if(method != null)
			{
				value = (T) method.invoke(null, name);
			}
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
}
