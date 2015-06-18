package roth.lib.map;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class Generic<T>
{
	protected Type type;
	
	protected Generic()
	{
		Type genericType = getClass().getGenericSuperclass();
		if(genericType instanceof Class)
		{
			throw new IllegalArgumentException("Missing type parameter.");
		}
		type = ((ParameterizedType) genericType).getActualTypeArguments()[0];
	}
	
	public Type getType()
	{
		return type;
	}
	
}
