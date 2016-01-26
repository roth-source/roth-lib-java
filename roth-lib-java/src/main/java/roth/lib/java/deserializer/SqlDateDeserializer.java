package roth.lib.java.deserializer;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SqlDateDeserializer extends TemporalDeserializer<Date>
{
	
	public SqlDateDeserializer()
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
				date = new Date(new SimpleDateFormat(timeFormat).parse(value).getTime());
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
