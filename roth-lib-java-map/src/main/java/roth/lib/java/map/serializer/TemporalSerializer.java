package roth.lib.java.map.serializer;

import java.text.SimpleDateFormat;

public abstract class TemporalSerializer<T> extends EscapedSerializer<T>
{
	protected SimpleDateFormat simpleDateFormat = null;
	
	protected TemporalSerializer()
	{
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String serialize(Object value, String timeFormat)
	{
		SimpleDateFormat simpleDateFormat = this.simpleDateFormat;
		if(timeFormat != null && !timeFormat.isEmpty())
		{
			simpleDateFormat = !timeFormat.equalsIgnoreCase("timestamp") ? new SimpleDateFormat(timeFormat) : null;
		}
		return serialize((T) value, simpleDateFormat);
	}
	
	public abstract String serialize(T temporal, SimpleDateFormat simpleDateFormat);
	
	@Override
	public boolean isEscapable()
	{
		return simpleDateFormat != null;
	}
	
	public String getTimeFormat()
	{
		return simpleDateFormat != null ? simpleDateFormat.toPattern() : null;
	}
	
	public void setTimeFormat(String timeFormat)
	{
		simpleDateFormat = timeFormat != null ? new SimpleDateFormat(timeFormat) : null;
	}
	
}
