package roth.lib.net.http.header.model;

import java.util.Calendar;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class Expires extends Header<Expires> implements Characters
{
	protected Calendar expires;
	
	public Expires()
	{
		
	}
	
	public Expires(String value)
	{
		this.value = value;
	}
	
	public Expires(Calendar expires)
	{
		this.expires = expires;
	}
	
	public Calendar getExpires()
	{
		return expires;
	}
	
	@Override
	public Expires deserialize(String value)
	{
		return new Expires(value);
	}
	
	@Override
	public Expires parse()
	{
		expires = HeaderUtil.parseCalendar(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(expires != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(HeaderUtil.formatCalendar(expires));
			return builder.toString();
		}
		return null;
	}
	
}
