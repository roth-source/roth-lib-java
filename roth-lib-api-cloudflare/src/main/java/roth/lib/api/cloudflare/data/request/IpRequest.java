package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class IpRequest extends CloudFlareRequest
{
	@Property(name = "ip")
	protected String ip;
	
	public IpRequest(String ip)
	{
		this.ip = ip;
	}
	
	public String getIp()
	{
		return ip;
	}
	
	public IpRequest setIp(String ip)
	{
		this.ip = ip;
		return this;
	}
	
	@Override
	public IpRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public IpRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public IpRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
