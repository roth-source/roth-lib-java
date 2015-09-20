package roth.lib.java.api.cloudflare.modify;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.CloudFlareRequest;

@Entity
@SuppressWarnings("serial")
public class BlacklistIpRequest extends CloudFlareRequest
{
	@Property(name = "key")
	protected String key;
	
	public BlacklistIpRequest(String ip)
	{
		this.key = ip;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public BlacklistIpRequest setKey(String key)
	{
		this.key = key;
		return this;
	}
	
	@Override
	public BlacklistIpRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public BlacklistIpRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public BlacklistIpRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
