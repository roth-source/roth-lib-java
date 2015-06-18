package roth.lib.map.deserializer;

import java.text.SimpleDateFormat;

import roth.lib.map.Deserializer;
import roth.lib.map.Temporal;

public abstract class TemporalDeserializer<T> extends Deserializer<T> implements Temporal
{
	protected SimpleDateFormat simpleDateFormat = null;
	
	protected TemporalDeserializer()
	{
		
	}
	
	@Override
	public String getTimeFormat()
	{
		return simpleDateFormat != null ? simpleDateFormat.toPattern() : null;
	}
	
	@Override
	public void setTimeFormat(String timeFormat)
	{
		if(timeFormat != null)
		{
			simpleDateFormat = new SimpleDateFormat(timeFormat);
		}
		else
		{
			simpleDateFormat = null;
		}
	}
	
	public Long getTime(String value)
	{
		Long time = null;
		try
		{
			if(simpleDateFormat != null)
			{
				time = simpleDateFormat.parse(value).getTime();
			}
			else
			{
				time = Long.parseLong(value);
			}
		}
		catch(Exception e)
		{
			
		}
		return time;
	}
	
}
