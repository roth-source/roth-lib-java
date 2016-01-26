package roth.lib.java.deserializer;

import roth.lib.java.time.Time;

public class TimeDeserializer extends TemporalDeserializer<Time>
{
	
	public TimeDeserializer()
	{
		
	}
	
	@Override
	public Time deserialize(String value, String timeFormat)
	{
		Time time = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				time = Time.parse(value, timeFormat);
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
