package roth.lib.map.deserializer;

import java.sql.Timestamp;

public class TimestampDeserializer extends TemporalDeserializer<Timestamp>
{
	
	public TimestampDeserializer()
	{
		
	}
	
	@Override
	public Timestamp deserialize(String value)
	{
		Timestamp object = null;
		Long time = getTime(value);
		if(time != null)
		{
			object = new Timestamp(time);
		}
		return object;
	}
	
}
