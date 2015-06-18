package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class IpActionRequest extends CloudFlareRequest
{
	@Property(name = "key")
	protected String key;
	
	public IpActionRequest(String ip)
	{
		this.key = ip;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public IpActionRequest setKey(String key)
	{
		this.key = key;
		return this;
	}
	
	@Override
	public IpActionRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public IpActionRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public IpActionRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
