package roth.lib.java.serializer;

import roth.lib.java.time.Time;

public class TimeSerializer extends TemporalSerializer<Time>
{
	
	public TimeSerializer()
	{
		super();
	}
	
	@Override
	public boolean isEscapable(Object value, String timeFormat)
	{
		boolean escapable = super.isEscapable(value, timeFormat);
		if(!escapable && !Time.class.equals(value.getClass()))
		{
			escapable = !"timestamp".equalsIgnoreCase(timeFormat);
		}
		return escapable;
	}
	
	@Override
	public String serializeValue(Time time, String timeFormat)
	{
		String value = null;
		try
		{
			if(time != null)
			{
				if(timeFormat != null && !timeFormat.isEmpty())
				{
					if("timestamp".equalsIgnoreCase(timeFormat))
					{
						value = String.valueOf(time.toTimestamp());
					}
					else
					{
						value = time.format(timeFormat);
					}
				}
				else
				{
					value = time.toString();
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return value;
	}
	
}
