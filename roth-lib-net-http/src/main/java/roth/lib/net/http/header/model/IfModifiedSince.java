package roth.lib.net.http.header.model;

import java.util.Calendar;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class IfModifiedSince extends Header<IfModifiedSince> implements Characters
{
	protected Calendar ifModifiedSince;
	
	public IfModifiedSince()
	{
		
	}
	
	public IfModifiedSince(String value)
	{
		this.value = value;
	}
	
	public IfModifiedSince(Calendar ifModifiedSince)
	{
		this.ifModifiedSince = ifModifiedSince;
	}
	
	public Calendar getIfModifiedSince()
	{
		return ifModifiedSince;
	}
	
	@Override
	public IfModifiedSince deserialize(String value)
	{
		return new IfModifiedSince(value);
	}
	
	@Override
	public IfModifiedSince parse()
	{
		ifModifiedSince = HeaderUtil.parseCalendar(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(ifModifiedSince != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(HeaderUtil.formatCalendar(ifModifiedSince));
			return builder.toString();
		}
		return null;
	}
	
}
