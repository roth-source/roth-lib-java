package roth.lib.net.http.header.model;

import java.util.Calendar;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class LastModified extends Header<LastModified> implements Characters
{
	protected Calendar lastModified;
	
	public LastModified()
	{
		
	}
	
	public LastModified(String value)
	{
		this.value = value;
	}
	
	public LastModified(Calendar lastModified)
	{
		this.lastModified = lastModified;
	}
	
	public Calendar getLastModified()
	{
		return lastModified;
	}
	
	@Override
	public LastModified deserialize(String value)
	{
		return new LastModified(value);
	}
	
	@Override
	public LastModified parse()
	{
		lastModified = HeaderUtil.parseCalendar(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(lastModified != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(HeaderUtil.formatCalendar(lastModified));
			return builder.toString();
		}
		return null;
	}
	
}
