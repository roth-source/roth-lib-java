package roth.lib.api.linode.data.request;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class LinodeRequest implements Serializable
{
	@Property(name = "api_key")
	protected String apiKey;
	
	@Property(name = "api_action")
	protected String apiAction;
	
	public LinodeRequest()
	{
		
	}
	
	public String getApiKey()
	{
		return apiKey;
	}
	
	public String getApiAction()
	{
		return apiAction;
	}
	
	public LinodeRequest setApiKey(String apiKey)
	{
		this.apiKey = apiKey;
		return this;
	}
	
	public LinodeRequest setApiAction(String apiAction)
	{
		this.apiAction = apiAction;
		return this;
	}
	
}
