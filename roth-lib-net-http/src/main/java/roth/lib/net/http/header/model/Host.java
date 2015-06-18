package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Host extends Header<Host> implements Characters
{
	
	public Host()
	{
		
	}
	
	public Host(String value)
	{
		this.value = value;
	}
	
	@Override
	public Host deserialize(String value)
	{
		return new Host(value);
	}
	
	@Override
	public Host parse()
	{
		return this;
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
