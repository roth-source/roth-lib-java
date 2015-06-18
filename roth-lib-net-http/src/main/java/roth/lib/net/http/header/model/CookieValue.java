package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class CookieValue implements Characters
{
	protected String name;
	protected String value;
	
	public CookieValue(String name, String value)
	{
		this.name = name;
		this.value = value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		if(name != null && value != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(name);
			builder.append(EQUAL);
			builder.append(value);
			return builder.toString();
		}
		return null;
	}
	
}
