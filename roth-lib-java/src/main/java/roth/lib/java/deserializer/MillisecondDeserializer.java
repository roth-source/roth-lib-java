package roth.lib.java.deserializer;

import roth.lib.java.time.Millisecond;

public class MillisecondDeserializer extends TemporalDeserializer<Millisecond>
{
	
	public MillisecondDeserializer()
	{
		
	}
	
	@Override
	public Millisecond deserialize(String value, String timeFormat)
	{
		Millisecond millisecond = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				millisecond = Millisecond.parse(value, timeFormat);
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
