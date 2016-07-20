package roth.lib.java.deserializer;

import java.sql.Time;

import roth.lib.java.time.TimeZone;

public class SqlTimestampDeserializer extends TemporalDeserializer<Time>
{
	
	public SqlTimestampDeserializer()
	{
		
	}
	
	@Override
	public Time deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Time time = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				time = new Time(timeZone.getFormatter(timeFormat).parse(value).getTime());
			}
			else
			{
				time = new Time(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return time;
	}
	
}
