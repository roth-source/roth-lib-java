package roth.lib.api.cloudflare.data.request;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class CloudFlareRequest implements Serializable
{
	@Property(name = "a")
	protected String action;
	
	@Property(name = "email")
	protected String email;
	
	@Property(name = "tkn")
	protected String token;
	
	public CloudFlareRequest()
	{
		
	}
	
	public String getAction()
	{
		return action;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public CloudFlareRequest setAction(String action)
	{
		this.action = action;
		return this;
	}
	
	public CloudFlareRequest setEmail(String email)
	{
		this.email = email;
		return this;
	}
	
	public CloudFlareRequest setToken(String token)
	{
		this.token = token;
		return this;
	}
	
}
