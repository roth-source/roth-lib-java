package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class CacheControlValue implements Characters
{
	protected String value;
	
	public CacheControlValue(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(value);
			return builder.toString();
		}
		return null;
	}
	
}
