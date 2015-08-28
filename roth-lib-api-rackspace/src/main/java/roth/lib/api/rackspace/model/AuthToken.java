package roth.lib.api.rackspace.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class AuthToken implements Serializable
{
	@Property(name = "id")
	protected String id;
	
	@Property(name = "expires")
	protected Calendar expires;
	
	public AuthToken()
	{
		
	}
	
	public String getId()
	{
		return id;
	}
	
	public Calendar getExpires()
	{
		return expires;
	}
	
	public AuthToken setId(String id)
	{
		this.id = id;
		return this;
	}
	
	public AuthToken setExpires(Calendar expires)
	{
		this.expires = expires;
		return this;
	}
	
	public boolean isExpired()
	{
		return expires.before(new GregorianCalendar());
	}
	
}
