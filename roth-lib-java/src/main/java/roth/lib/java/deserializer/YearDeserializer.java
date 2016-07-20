package roth.lib.java.deserializer;

import roth.lib.java.time.TimeZone;
import roth.lib.java.time.Year;
import roth.lib.java.util.NumberUtil;

public class YearDeserializer extends TemporalDeserializer<Year>
{
	
	public YearDeserializer()
	{
		
	}
	
	@Override
	public Year deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Year year = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				year = Year.parse(value, timeZone, timeFormat);
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
					year = Year.parse(value, timeZone);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return year;
	}
	
}
