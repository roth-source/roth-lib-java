package roth.lib.net.http.header.model;

import java.util.Calendar;

import roth.lib.Characters;
import roth.lib.net.http.header.HeaderUtil;

public class RetryAfter extends Header<RetryAfter> implements Characters
{
	protected Calendar date;
	protected Integer seconds;
	
	public RetryAfter()
	{
		
	}
	
	public RetryAfter(String value)
	{
		this.value = value;
	}
	
	public RetryAfter(Calendar date)
	{
		this.date = date;
	}
	
	public RetryAfter(Integer seconds)
	{
		this.seconds = seconds;
	}
	
	public Calendar getDate()
	{
		return date;
	}
	
	public Integer getSeconds()
	{
		return seconds;
	}
	
	@Override
	public RetryAfter deserialize(String value)
	{
		return new RetryAfter(value);
	}
	
	@Override
	public RetryAfter parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(date != null || seconds != null)
		{
			StringBuilder builder = new StringBuilder();
			if(date != null)
			{
				builder.append(HeaderUtil.formatCalendar(date));
			}
			else
			{
				builder.append(seconds);
			}
			return builder.toString();
		}
		return null;
	}
	
}
