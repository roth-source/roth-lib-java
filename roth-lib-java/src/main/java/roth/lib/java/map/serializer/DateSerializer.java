package roth.lib.java.map.serializer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends TemporalSerializer<Date>
{
	
	public DateSerializer()
	{
		super();
	}
	
	@Override
	public String serialize(Date date)
	{
		return serialize(date, simpleDateFormat);
	}
	
	@Override
	public String serialize(Date date, SimpleDateFormat simpleDateFormat)
	{
		if(date != null)
		{
			if(simpleDateFormat != null)
			{
				return simpleDateFormat.format(date);
			}
			else
			{
				return String.valueOf(date.getTime());
			}
		}
		return null;
	}
	
}
