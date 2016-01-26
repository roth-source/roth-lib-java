package roth.lib.java.deserializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SqlTimeDeserializer extends TemporalDeserializer<Timestamp>
{
	
	public SqlTimeDeserializer()
	{
		
	}
	
	@Override
	public Timestamp deserialize(String value, String timeFormat)
	{
		Timestamp timestamp = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				timestamp = new Timestamp(new SimpleDateFormat(timeFormat).parse(value).getTime());
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
