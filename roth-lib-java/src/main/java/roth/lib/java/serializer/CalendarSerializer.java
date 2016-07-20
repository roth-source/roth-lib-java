package roth.lib.java.serializer;

import java.util.Calendar;

import roth.lib.java.time.TimeZone;

public class CalendarSerializer extends TemporalSerializer<Calendar>
{
	
	public CalendarSerializer()
	{
		super();
	}
	
	@Override
	public String serializeValue(Calendar calendar, TimeZone timeZone, String timeFormat)
	{
		String value = null;
		try
		{
			if(calendar != null)
			{
				if(isEscapable(calendar, timeZone, timeFormat))
				{
					value = timeZone.getFormatter(timeFormat).format(calendar.getTime());
				}
				else
				{
					value = String.valueOf(calendar.getTimeInMillis());
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
}
