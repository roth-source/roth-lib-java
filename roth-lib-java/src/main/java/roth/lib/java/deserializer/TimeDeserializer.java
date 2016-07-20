package roth.lib.java.deserializer;

import roth.lib.java.time.Time;
import roth.lib.java.time.TimeZone;

public class TimeDeserializer extends TemporalDeserializer<Time>
{
	
	public TimeDeserializer()
	{
		
	}
	
	@Override
	public Time deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Time time = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				time = Time.parse(value, timeZone, timeFormat);
			}
			else
			{
				time = new Time(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return time;
	}
	
}
