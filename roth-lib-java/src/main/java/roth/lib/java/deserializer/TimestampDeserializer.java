package roth.lib.java.deserializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampDeserializer extends TemporalDeserializer<Timestamp>
{
	
	public TimestampDeserializer()
	{
		
	}
	
	@Override
	public Timestamp deserialize(String value, SimpleDateFormat simpleDateFormat)
	{
		Timestamp object = null;
		Long time = getTime(value, simpleDateFormat);
		if(time != null)
		{
			object = new Timestamp(time);
		}
		return object;
	}
	
}
