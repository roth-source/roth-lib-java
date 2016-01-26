package roth.lib.java.deserializer;

import roth.lib.java.time.Second;

public class SecondDeserializer extends TemporalDeserializer<Second>
{
	
	public SecondDeserializer()
	{
		
	}
	
	@Override
	public Second deserialize(String value, String timeFormat)
	{
		Second second = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				second = Second.parse(value, timeFormat);
			}
			else
			{
				second = new Second(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return second;
	}
	
}
