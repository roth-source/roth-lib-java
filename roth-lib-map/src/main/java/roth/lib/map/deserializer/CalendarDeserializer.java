package roth.lib.map.deserializer;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CalendarDeserializer extends TemporalDeserializer<GregorianCalendar>
{
	
	public CalendarDeserializer()
	{
		
	}
	
	@Override
	public GregorianCalendar deserialize(String value, SimpleDateFormat simpleDateFormat)
	{
		GregorianCalendar object = null;
		Long time = getTime(value, simpleDateFormat);
		if(time != null)
		{
			object = new GregorianCalendar();
			object.setTimeInMillis(time);
		}
		return object;
	}
	
}
