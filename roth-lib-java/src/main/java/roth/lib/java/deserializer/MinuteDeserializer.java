package roth.lib.java.deserializer;

import roth.lib.java.time.Minute;
import roth.lib.java.time.TimeZone;

public class MinuteDeserializer extends TemporalDeserializer<Minute>
{
	
	public MinuteDeserializer()
	{
		
	}
	
	@Override
	public Minute deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Minute minute = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				minute = Minute.parse(value, timeZone, timeFormat);
			}
			else
			{
				minute = new Minute(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return minute;
	}
	
}
