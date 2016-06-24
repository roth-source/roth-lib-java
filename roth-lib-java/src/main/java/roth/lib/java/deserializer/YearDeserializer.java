package roth.lib.java.deserializer;

import roth.lib.java.time.Year;
import roth.lib.java.util.NumberUtil;

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
				Long time = NumberUtil.parseLong(value);
				if(time != null && time > 9999L)
				{
					year = new Year(time);
				}
				else
				{
					year = Year.fromString(value);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return year;
	}
	
}
