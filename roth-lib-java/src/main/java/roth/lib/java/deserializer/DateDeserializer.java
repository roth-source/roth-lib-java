package roth.lib.java.deserializer;

import java.util.Date;

import roth.lib.java.time.TimeZone;

public class DateDeserializer extends TemporalDeserializer<Date>
{
	
	public DateDeserializer()
	{
		
	}
	
	@Override
	public Date deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Date date = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				date = timeZone.getFormatter(timeFormat).parse(value);
			}
			else
			{
				date = new Date(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return date;
	}
	
}
