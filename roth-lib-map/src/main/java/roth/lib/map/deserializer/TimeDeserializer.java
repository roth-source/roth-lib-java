package roth.lib.map.deserializer;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class TimeDeserializer extends TemporalDeserializer<Time>
{
	
	public TimeDeserializer()
	{
		
	}
	
	@Override
	public Time deserialize(String value, SimpleDateFormat simpleDateFormat)
	{
		Time object = null;
		Long time = getTime(value, simpleDateFormat);
		if(time != null)
		{
			object = new Time(time);
		}
		return object;
	}
	
}
