package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.CloudFlareRequest;

@Entity
@SuppressWarnings("serial")
public class UnlistIpRequest extends CloudFlareRequest
{
	@Property(name = "key")
	protected String key;
	
	public UnlistIpRequest(String ip)
	{
		this.key = ip;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public UnlistIpRequest setKey(String key)
	{
		this.key = key;
		return this;
	}
	
	@Override
	public UnlistIpRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public UnlistIpRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public UnlistIpRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
