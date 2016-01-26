package roth.lib.java.deserializer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends TemporalDeserializer<Date>
{
	
	public DateDeserializer()
	{
		
	}
	
	@Override
	public Date deserialize(String value, String timeFormat)
	{
		Date date = null;
		try
		{
			if(timeFormat != null && !timeFormat.isEmpty() && !"timestamp".equalsIgnoreCase(timeFormat))
			{
				date = new SimpleDateFormat(timeFormat).parse(value);
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
