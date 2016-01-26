package roth.lib.java.deserializer;

import roth.lib.java.time.Year;

public class YearDeserializer extends TemporalDeserializer<Year>
{
	
	public YearDeserializer()
	{
		
	}
	
	@Override
	public Year deserialize(String value, String timeFormat)
	{
		Year year = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				year = Year.parse(value, timeFormat);
			}
			else
			{
				year = new Year(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return year;
	}
	
}
