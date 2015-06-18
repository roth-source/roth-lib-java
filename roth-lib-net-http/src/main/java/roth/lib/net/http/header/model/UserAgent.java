package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class UserAgent extends Header<UserAgent> implements Characters
{
	
	public UserAgent()
	{
		
	}
	
	public UserAgent(String value)
	{
		this.value = value;
	}
	
	@Override
	public UserAgent deserialize(String value)
	{
		return new UserAgent(value);
	}
	
	@Override
	public UserAgent parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
