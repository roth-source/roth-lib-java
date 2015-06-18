package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class AccessControlAllowOrigin extends Header<AccessControlAllowOrigin> implements Characters
{
	
	public AccessControlAllowOrigin()
	{
		
	}
	
	public AccessControlAllowOrigin(String value)
	{
		this.value = value;
	}
	
	@Override
	public AccessControlAllowOrigin deserialize(String value)
	{
		return new AccessControlAllowOrigin(value);
	}
	
	@Override
	public AccessControlAllowOrigin parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
