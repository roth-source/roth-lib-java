package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ProxyAuthenticate extends Header<ProxyAuthenticate> implements Characters
{
	
	public ProxyAuthenticate()
	{
		
	}
	
	public ProxyAuthenticate(String value)
	{
		this.value = value;
	}
	
	@Override
	public ProxyAuthenticate deserialize(String value)
	{
		return new ProxyAuthenticate(value);
	}
	
	@Override
	public ProxyAuthenticate parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
