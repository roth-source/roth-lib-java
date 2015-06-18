package roth.lib.map.serializer;

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
