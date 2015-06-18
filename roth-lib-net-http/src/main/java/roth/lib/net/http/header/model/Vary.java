package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Vary extends Header<Vary> implements Characters
{
	
	public Vary()
	{
		
	}
	
	public Vary(String value)
	{
		this.value = value;
	}
	
	@Override
	public Vary deserialize(String value)
	{
		return new Vary(value);
	}
	
	@Override
	public Vary parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
