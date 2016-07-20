package roth.lib.java.deserializer;

import java.util.Calendar;
import java.util.GregorianCalendar;

import roth.lib.java.time.TimeZone;

public class CalendarDeserializer extends TemporalDeserializer<Calendar>
{
	
	public CalendarDeserializer()
	{
		
	}
	
	@Override
	public GregorianCalendar deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		GregorianCalendar calendar = null;
		try
		{
			calendar = new GregorianCalendar();
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				calendar.setTime(timeZone.getFormatter(timeFormat).parse(value));
			}
			else
			{
				calendar.setTimeInMillis(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			calendar = null;
		}
		return calendar;
	}
	
}
