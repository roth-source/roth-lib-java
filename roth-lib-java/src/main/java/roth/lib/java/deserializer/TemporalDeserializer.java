package roth.lib.java.deserializer;

import java.text.SimpleDateFormat;

public abstract class TemporalDeserializer<T> extends Deserializer<T>
{
	protected SimpleDateFormat simpleDateFormat = null;
	
	protected TemporalDeserializer()
	{
		
	}
	
	public abstract T deserialize(String value, SimpleDateFormat simpleDateFormat);
	
	@Override
	public T deserialize(String value, String timeFormat)
	{
		SimpleDateFormat simpleDateFormat = this.simpleDateFormat;
		if(timeFormat != null && !timeFormat.isEmpty())
		{
			simpleDateFormat = !timeFormat.equalsIgnoreCase("timestamp") ? new SimpleDateFormat(timeFormat) : null;
		}
		return deserialize(value, simpleDateFormat);
	}
	
	public String getTimeFormat()
	{
		return simpleDateFormat != null ? simpleDateFormat.toPattern() : null;
	}
	
	public void setTimeFormat(String timeFormat)
	{
		simpleDateFormat = timeFormat != null ? new SimpleDateFormat(timeFormat) : null;
	}
	
	public Long getTime(String value, SimpleDateFormat simpleDateFormat)
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
