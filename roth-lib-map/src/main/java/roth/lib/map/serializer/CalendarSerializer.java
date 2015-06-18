package roth.lib.map.serializer;

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
