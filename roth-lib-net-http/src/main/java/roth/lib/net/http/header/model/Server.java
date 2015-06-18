package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Server extends Header<Server> implements Characters
{
	
	public Server()
	{
		
	}
	
	public Server(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public Server deserialize(String value)
	{
		return new Server(value);
	}
	
	@Override
	public Server parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
