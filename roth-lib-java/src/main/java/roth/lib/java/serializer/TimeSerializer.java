package roth.lib.java.serializer;

import roth.lib.java.time.Time;
import roth.lib.java.time.TimeZone;

public class TimeSerializer extends TemporalSerializer<Time>
{
	
	public TimeSerializer()
	{
		super();
	}
	
	@Override
	public boolean isEscapable(Object value, TimeZone timeZone, String timeFormat)
	{
		boolean escapable = super.isEscapable(value, timeZone, timeFormat);
		if(!escapable && !Time.class.equals(value.getClass()))
		{
			escapable = !"timestamp".equalsIgnoreCase(timeFormat);
		}
		return escapable;
	}
	
	@Override
	public String serializeValue(Time time, TimeZone timeZone, String timeFormat)
	{
		String value = null;
		try
		{
			if(time != null)
			{
				if(timeFormat != null && !timeFormat.isEmpty())
				{
					if("timestamp".equalsIgnoreCase(timeFormat))
					{
						value = String.valueOf(time.toTimestamp());
					}
					else
					{
						value = time.format(timeFormat, timeZone);
					}
				}
				else
				{
					value = time.format(timeZone);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
}
