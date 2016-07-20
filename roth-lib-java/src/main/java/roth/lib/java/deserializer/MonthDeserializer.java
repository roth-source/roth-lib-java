package roth.lib.java.deserializer;

import roth.lib.java.time.Month;
import roth.lib.java.time.TimeZone;
import roth.lib.java.util.NumberUtil;

public class MonthDeserializer extends TemporalDeserializer<Month>
{
	
	public MonthDeserializer()
	{
		
	}
	
	@Override
	public Month deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Month month = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				month = Month.parse(value, timeZone, timeFormat);
			}
			else
			{
				Long time = NumberUtil.parseLong(value);
				if(time != null)
				{
					month = new Month(time);
				}
				else
				{
					month = Month.parse(value, timeZone);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return month;
	}
	
}
