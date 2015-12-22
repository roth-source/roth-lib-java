package roth.lib.java.deserializer;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateDeserializer extends TemporalDeserializer<Date>
{
	
	public DateDeserializer()
	{
		
	}
	
	@Override
	public Date deserialize(String value, SimpleDateFormat simpleDateFormat)
	{
		Date object = null;
		Long time = getTime(value, simpleDateFormat);
		if(time != null)
		{
			object = new Date(time);
		}
		return object;
	}
	
}
