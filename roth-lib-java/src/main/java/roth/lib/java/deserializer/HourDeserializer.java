package roth.lib.java.deserializer;

import roth.lib.java.time.Hour;

public class HourDeserializer extends TemporalDeserializer<Hour>
{
	
	public HourDeserializer()
	{
		
	}
	
	@Override
	public Hour deserialize(String value, String timeFormat)
	{
		Hour hour = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				hour = Hour.parse(value, timeFormat);
			}
			else
			{
				hour = new Hour(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return hour;
	}
	
}
