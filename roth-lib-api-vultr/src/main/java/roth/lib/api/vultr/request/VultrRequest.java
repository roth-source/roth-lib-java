package roth.lib.api.vultr.request;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class VultrRequest implements Serializable
{
	@Property(name = "api_key")
	protected String apiKey;
	
	public VultrRequest()
	{
		
	}
	
	public String getApiKey()
	{
		return apiKey;
	}
	
	public VultrRequest setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
		return this;
	}
	
}
