package roth.lib.java.deserializer;

import roth.lib.java.time.Day;

public class DayDeserializer extends TemporalDeserializer<Day>
{
	
	public DayDeserializer()
	{
		
	}
	
	@Override
	public Day deserialize(String value, String timeFormat)
	{
		Day day = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				day = Day.parse(value, timeFormat);
			}
			else
			{
				day = new Day(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return day;
	}
	
}
