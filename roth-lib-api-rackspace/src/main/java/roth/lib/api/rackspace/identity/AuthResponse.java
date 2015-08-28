package roth.lib.api.rackspace.identity;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.rackspace.model.Access;

@SuppressWarnings("serial")
public class AuthResponse implements Serializable
{
	@Property(name = "access")
	protected Access access;
	
	public AuthResponse()
	{
		
	}
	
	public Access getAccess()
	{
		return access;
	}
	
	public AuthResponse setAccess(Access access)
	{
		this.access = access;
		return this;
	}
	
}
