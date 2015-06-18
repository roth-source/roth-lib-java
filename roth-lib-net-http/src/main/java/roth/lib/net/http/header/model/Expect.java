package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Expect extends Header<Expect> implements Characters
{
	
	public Expect()
	{
		
	}
	
	public Expect(String value)
	{
		this.value = value;
	}
	
	@Override
	public Expect deserialize(String value)
	{
		return new Expect(value);
	}
	
	@Override
	public Expect parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
