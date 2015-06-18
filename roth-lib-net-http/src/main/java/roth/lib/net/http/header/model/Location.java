package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Location extends Header<Location> implements Characters
{
	
	public Location()
	{
		
	}
	
	public Location(String value)
	{
		this.value = value;
	}
	
	@Override
	public Location deserialize(String value)
	{
		return new Location(value);
	}
	
	@Override
	public Location parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
