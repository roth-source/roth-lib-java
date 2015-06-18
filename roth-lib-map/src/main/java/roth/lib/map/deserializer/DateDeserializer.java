package roth.lib.map.deserializer;

import java.sql.Date;

public class DateDeserializer extends TemporalDeserializer<Date>
{
	
	public DateDeserializer()
	{
		
	}
	
	@Override
	public Date deserialize(String value)
	{
		Date object = null;
		Long time = getTime(value);
		if(time != null)
		{
			object = new Date(time);
		}
		return object;
	}
	
}
