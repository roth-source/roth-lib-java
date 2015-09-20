package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class RequestsServed implements Serializable
{
	@Property(name = "cloudflare")
	protected Integer cloudflare;
	
	@Property(name = "user")
	protected Integer user;
	
	public RequestsServed()
	{
		
	}
	
	public Integer getCloudflare()
	{
		return cloudflare;
	}
	
	public Integer getUser()
	{
		return user;
	}
	
	public RequestsServed setCloudflare(Integer cloudflare)
	{
		this.cloudflare = cloudflare;
		return this;
	}
	
	public RequestsServed setUser(Integer user)
	{
		this.user = user;
		return this;
	}
	
}
