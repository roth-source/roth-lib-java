package roth.lib.java.serializer;

import roth.lib.java.time.TimeZone;

public class EscapedSerializer<T> extends Serializer<T>
{
	
	public EscapedSerializer()
	{
		
	}
	
	@Override
	public boolean isEscapable(Object value, TimeZone timeZone, String timeFormat)
	{
		return true;
	}
	
	@Override
	public String serializeValue(T object)
	{
		return object != null ? object.toString() : null;
	}
	
}
