package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Trailer extends Header<Trailer> implements Characters
{
	
	public Trailer()
	{
		
	}
	
	public Trailer(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public Trailer deserialize(String value)
	{
		return new Trailer(value);
	}
	
	@Override
	public Trailer parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
