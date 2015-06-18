package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class From extends Header<From> implements Characters
{
	
	public From()
	{
		
	}
	
	public From(String value)
	{
		this.value = value;
	}
	
	@Override
	public From deserialize(String value)
	{
		return new From(value);
	}
	
	@Override
	public From parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
