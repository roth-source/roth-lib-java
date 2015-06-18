package roth.lib.map.deserializer;

import java.sql.Time;

public class TimeDeserializer extends TemporalDeserializer<Time>
{
	
	public TimeDeserializer()
	{
		
	}
	
	@Override
	public Time deserialize(String value)
	{
		Time object = null;
		Long time = getTime(value);
		if(time != null)
		{
			object = new Time(time);
		}
		return object;
	}
	
}
