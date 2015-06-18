package roth.lib.net.http.header.model;

import java.util.Calendar;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class Date extends Header<Date> implements Characters
{
	protected Calendar date;
	
	public Date()
	{
		
	}
	
	public Date(String value)
	{
		this.value = value;
	}
	
	public Date(Calendar date)
	{
		this.date = date;
	}
	
	public Calendar getDate()
	{
		return date;
	}
	
	@Override
	public Date deserialize(String value)
	{
		return new Date(value);
	}
	
	@Override
	public Date parse()
	{
		date = HeaderUtil.parseCalendar(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(date != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(HeaderUtil.formatCalendar(date));
			return builder.toString();
		}
		return null;
	}
	
}
