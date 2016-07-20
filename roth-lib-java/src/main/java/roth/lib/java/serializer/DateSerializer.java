package roth.lib.java.serializer;

import java.util.Date;

import roth.lib.java.time.TimeZone;

public class DateSerializer extends TemporalSerializer<Date>
{
	
	public DateSerializer()
	{
		super();
	}
	
	@Override
	public String serializeValue(Date date, TimeZone timeZone, String timeFormat)
	{
		String value = null;
		try
		{
			if(date != null)
			{
				if(isEscapable(date, timeZone, timeFormat))
				{
					value = timeZone.getFormatter(timeFormat).format(date);
				}
				else
				{
					value = String.valueOf(date.getTime());
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
}
