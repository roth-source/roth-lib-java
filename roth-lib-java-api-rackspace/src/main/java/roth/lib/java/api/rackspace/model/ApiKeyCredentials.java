package roth.lib.java.api.rackspace.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class ApiKeyCredentials implements Serializable
{
	@Property(name = "username")
	protected String username;
	
	@Property(name = "apiKey")
	protected String apiKey;
	
	public ApiKeyCredentials(String username, String apiKey)
	{
		this.username = username;
		this.apiKey = apiKey;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getApiKey()
	{
		return apiKey;
	}
	
	public ApiKeyCredentials setUsername(String username)
	{
		this.username = username;
		return this;
	}
	
	public ApiKeyCredentials setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
		return this;
	}
	
}
