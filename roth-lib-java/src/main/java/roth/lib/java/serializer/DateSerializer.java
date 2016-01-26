package roth.lib.java.serializer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends TemporalSerializer<Date>
{
	
	public DateSerializer()
	{
		super();
	}
	
	@Override
	public String serializeValue(Date date, String timeFormat)
	{
		String value = null;
		try
		{
			if(date != null)
			{
				if(isEscapable(date, timeFormat))
				{
					value = new SimpleDateFormat(timeFormat).format(date);
				}
				else
				{
					value = String.valueOf(date.getTime());
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
}
