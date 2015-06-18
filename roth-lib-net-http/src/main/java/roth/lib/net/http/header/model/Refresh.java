package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Refresh extends Header<Refresh> implements Characters
{
	protected Integer seconds;
	protected String url;
	
	public Refresh()
	{
		
	}
	
	public Refresh(String value)
	{
		this.value = value;
	}
	
	public Refresh(Integer seconds, String url)
	{
		this.seconds = seconds;
		this.url = url;
	}
	
	public Integer getSeconds()
	{
		return seconds;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	@Override
	public Refresh deserialize(String value)
	{
		return new Refresh(value);
	}
	
	@Override
	public Refresh parse()
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
		else if(seconds != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(seconds);
			if(url != null)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("url");
				builder.append(EQUAL);
				builder.append(url);
				
			}
			return builder.toString();
		}
		return null;
	}
	
}
