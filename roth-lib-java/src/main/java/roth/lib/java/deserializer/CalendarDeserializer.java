package roth.lib.java.deserializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarDeserializer extends TemporalDeserializer<Calendar>
{
	
	public CalendarDeserializer()
	{
		
	}
	
	@Override
	public GregorianCalendar deserialize(String value, String timeFormat)
	{
		GregorianCalendar calendar = null;
		try
		{
			calendar = new GregorianCalendar();
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				calendar.setTime(new SimpleDateFormat(timeFormat).parse(value));
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
