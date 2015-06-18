package roth.lib.net.http.header.model;

import java.util.Calendar;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class IfUnmodifiedSince extends Header<IfUnmodifiedSince> implements Characters
{
	protected Calendar ifUnmodifiedSince;
	
	public IfUnmodifiedSince()
	{
		
	}
	
	public IfUnmodifiedSince(String value)
	{
		this.value = value;
	}
	
	public IfUnmodifiedSince(Calendar ifUnmodifiedSince)
	{
		this.ifUnmodifiedSince = ifUnmodifiedSince;
	}
	
	public Calendar getIfUnmodifiedSince()
	{
		return ifUnmodifiedSince;
	}
	
	@Override
	public IfUnmodifiedSince deserialize(String value)
	{
		return new IfUnmodifiedSince(value);
	}
	
	@Override
	public IfUnmodifiedSince parse()
	{
		ifUnmodifiedSince = HeaderUtil.parseCalendar(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(ifUnmodifiedSince != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(HeaderUtil.formatCalendar(ifUnmodifiedSince));
			return builder.toString();
		}
		return null;
	}
	
}
