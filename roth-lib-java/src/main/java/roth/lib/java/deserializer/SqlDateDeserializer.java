package roth.lib.java.deserializer;

import java.sql.Date;

import roth.lib.java.time.TimeZone;

public class SqlDateDeserializer extends TemporalDeserializer<Date>
{
	
	public SqlDateDeserializer()
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
				date = new Date(timeZone.getFormatter(timeFormat).parse(value).getTime());
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
