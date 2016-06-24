package roth.lib.java.deserializer;

import roth.lib.java.time.Day;
import roth.lib.java.util.NumberUtil;

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
				Long time = NumberUtil.parseLong(value);
				if(time != null)
				{
					day = new Day(time);
				}
				else
				{
					day = Day.fromString(value);
				}
				
			}
		}
		catch(Exception e)
		{
			
		}
		return day;
	}
	
}
