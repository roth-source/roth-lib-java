package roth.lib.map.deserializer;

import java.util.GregorianCalendar;

public class CalendarDeserializer extends TemporalDeserializer<GregorianCalendar>
{
	
	public CalendarDeserializer()
	{
		
	}
	
	@Override
	public GregorianCalendar deserialize(String value)
	{
		GregorianCalendar object = null;
		Long time = getTime(value);
		if(time != null)
		{
			object = new GregorianCalendar();
			object.setTimeInMillis(time);
		}
		return object;
	}
	
}
