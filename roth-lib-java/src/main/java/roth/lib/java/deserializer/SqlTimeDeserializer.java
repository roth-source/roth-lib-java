package roth.lib.java.deserializer;

import java.sql.Timestamp;

import roth.lib.java.time.TimeZone;

public class SqlTimeDeserializer extends TemporalDeserializer<Timestamp>
{
	
	public SqlTimeDeserializer()
	{
		
	}
	
	@Override
	public Timestamp deserialize(String value, TimeZone timeZone, String timeFormat)
	{
		Timestamp timestamp = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				timestamp = new Timestamp(timeZone.getFormatter(timeFormat).parse(value).getTime());
			}
			else
			{
				timestamp = new Timestamp(Long.parseLong(value));
			}
		}
		catch(Exception e)
		{
			
		}
		return timestamp;
	}
	
}
