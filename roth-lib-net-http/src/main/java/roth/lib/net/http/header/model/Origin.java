package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Origin extends Header<Origin> implements Characters
{
	
	public Origin()
	{
		
	}
	
	public Origin(String value)
	{
		this.value = value;
	}
	
	@Override
	public Origin deserialize(String value)
	{
		return new Origin(value);
	}
	
	@Override
	public Origin parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
