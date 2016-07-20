package roth.lib.java.serializer;

import roth.lib.java.time.TimeZone;

public abstract class TemporalSerializer<T> extends EscapedSerializer<T>
{
	
	protected TemporalSerializer()
	{
		
	}
	
	@Override
	public boolean isEscapable(Object value, TimeZone timeZone, String timeFormat)
	{
		return timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat);
	}
	
}
