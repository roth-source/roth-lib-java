package roth.lib.java.serializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarSerializer extends TemporalSerializer<Calendar>
{
	
	public CalendarSerializer()
	{
		super();
	}
	
	@Override
	public String serializeValue(Calendar calendar, String timeFormat)
	{
		String value = null;
		try
		{
			if(calendar != null)
			{
				if(isEscapable(calendar, timeFormat))
				{
					value = new SimpleDateFormat(timeFormat).format(calendar.getTime());
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
