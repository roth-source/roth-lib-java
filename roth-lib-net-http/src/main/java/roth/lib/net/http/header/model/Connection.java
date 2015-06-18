package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Connection extends Header<Connection> implements Characters
{
	
	public Connection()
	{
		
	}
	
	public Connection(String value)
	{
		this.value = value;
	}
	
	@Override
	public Connection deserialize(String value)
	{
		return new Connection(value);
	}
	
	@Override
	public Connection parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
