package roth.lib.java.deserializer;

import roth.lib.java.time.Month;

public class MonthDeserializer extends TemporalDeserializer<Month>
{
	
	public MonthDeserializer()
	{
		
	}
	
	@Override
	public Month deserialize(String value, String timeFormat)
	{
		Month month = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				month = Month.parse(value, timeFormat);
			}
			else
			{
				month = new Month(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return month;
	}
	
}
