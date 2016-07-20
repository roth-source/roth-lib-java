package roth.lib.java.serializer;

import roth.lib.java.time.TimeZone;

public abstract class Serializer<T>
{
	
	protected Serializer()
	{
		
	}
	
	public boolean isEscapable(Object value, TimeZone timeZone, String timeFormat)
	{
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String serialize(Object value, TimeZone timeZone, String timeFormat)
	{
		return serializeValue((T) value, timeZone, timeFormat);
	}
	
	public String serializeValue(T value, TimeZone timeZone, String timeFormat)
	{
		return serializeValue((T) value);
	}
	
	public String serializeValue(T value)
	{
		return null;
	}
	
}
