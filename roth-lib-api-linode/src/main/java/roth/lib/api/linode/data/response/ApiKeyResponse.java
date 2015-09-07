package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class ApiKeyResponse implements Serializable
{
	@Property(name = "USERNAME")
	protected String username;
	
	@Property(name = "API_KEY")
	protected String apiKey;
	
	public ApiKeyResponse()
	{
		
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getApiKey()
	{
		return apiKey;
	}
	
	public ApiKeyResponse setUsername(String username)
	{
		this.username = username;
		return this;
	}
	
	public ApiKeyResponse setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
		return this;
	}
	
}
