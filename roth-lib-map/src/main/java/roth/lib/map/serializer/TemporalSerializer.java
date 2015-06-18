package roth.lib.map.serializer;

import java.text.SimpleDateFormat;

import roth.lib.map.Temporal;

public abstract class TemporalSerializer<T> extends EscapedSerializer<T> implements Temporal
{
	protected SimpleDateFormat simpleDateFormat = null;
	
	protected TemporalSerializer()
	{
		
	}
	
	@Override
	public boolean isEscapable()
	{
		return simpleDateFormat != null;
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
	
}
