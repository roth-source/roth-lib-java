package roth.lib.java.map.serializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarSerializer extends TemporalSerializer<Calendar>
{
	
	public CalendarSerializer()
	{
		super();
	}
	
	@Override
	public String serialize(Calendar calendar)
	{
		return serialize(calendar, simpleDateFormat);
	}
	
	@Override
	public String serialize(Calendar calendar, SimpleDateFormat simpleDateFormat)
	{
		if(calendar != null)
		{
			if(simpleDateFormat != null)
			{
				return simpleDateFormat.format(calendar.getTime());
			}
			else
			{
				return String.valueOf(calendar.getTimeInMillis());
			}
		}
		return null;
	}
	
}
