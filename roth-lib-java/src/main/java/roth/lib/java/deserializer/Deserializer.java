package roth.lib.java.deserializer;

import roth.lib.java.time.TimeZone;

public abstract class Deserializer<T>
{
	
	protected Deserializer()
	{
		
	}
	
	public T deserialize(String value, TimeZone timeZone, String timeFormat, Class<?> klass)
	{
		return deserialize(value, timeZone, timeFormat);
	}
	
	public T deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		return deserialize(value);
	}
	
	public T deserialize(String value)
	{
		return null;
	}
	
}
