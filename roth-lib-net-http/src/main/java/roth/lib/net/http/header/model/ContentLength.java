package roth.lib.net.http.header.model;

import roth.lib.Characters;
import roth.lib.util.NumberUtil;

public class ContentLength extends Header<ContentLength> implements Characters
{
	protected Long length;
	
	public ContentLength()
	{
		
	}
	
	public ContentLength(String value)
	{
		this.value = value;
	}
	
	public ContentLength(Long length)
	{
		this.length = length;
	}
	
	public Long getLength()
	{
		return length;
	}
	
	@Override
	public ContentLength deserialize(String value)
	{
		return new ContentLength(value);
	}
	
	@Override
	public ContentLength parse()
	{
		length = NumberUtil.parseLong(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			builder.append(length);
			return builder.toString();
		}
	}
	
}
