package roth.lib.java.deserializer;

import roth.lib.java.time.Millisecond;
import roth.lib.java.time.TimeZone;

public class MillisecondDeserializer extends TemporalDeserializer<Millisecond>
{
	
	public MillisecondDeserializer()
	{
		
	}
	
	@Override
	public Millisecond deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Millisecond millisecond = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				millisecond = Millisecond.parse(value, timeZone, timeFormat);
			}
			else
			{
				millisecond = new Millisecond(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return millisecond;
	}
	
}
