package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class WwwAuthenticate extends Header<WwwAuthenticate> implements Characters
{
	
	public WwwAuthenticate()
	{
		
	}
	
	public WwwAuthenticate(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public WwwAuthenticate deserialize(String value)
	{
		return new WwwAuthenticate(value);
	}
	
	@Override
	public WwwAuthenticate parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
