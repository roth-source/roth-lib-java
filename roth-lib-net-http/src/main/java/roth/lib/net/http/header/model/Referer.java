package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Referer extends Header<Referer> implements Characters
{
	
	public Referer()
	{
		
	}
	
	public Referer(String value)
	{
		this.value = value;
	}
	
	@Override
	public Referer deserialize(String value)
	{
		return new Referer(value);
	}
	
	@Override
	public Referer parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
