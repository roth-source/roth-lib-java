package roth.lib.api.cloudflare.access;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.CloudFlareRequest;

@Entity
@SuppressWarnings("serial")
public class GetIpRequest extends CloudFlareRequest
{
	@Property(name = "ip")
	protected String ip;
	
	public GetIpRequest(String ip)
	{
		this.ip = ip;
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public GetIpRequest setIp(String ip)
	{
		this.ip = ip;
		return this;
	}
	
	@Override
	public GetIpRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public GetIpRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public GetIpRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
