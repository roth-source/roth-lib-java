package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RefreshRequest extends CloudFlareRequest
{
	@Property(name = "zid")
	protected Integer zoneId;
	
	public RefreshRequest(int zoneId)
	{
		this.zoneId = zoneId;
	}
	
	public Integer getZoneId()
	{
		return zoneId;
	}
	
	public RefreshRequest setZoneId(Integer zoneId)
	{
		this.zoneId = zoneId;
		return this;
	}
	
	@Override
	public RefreshRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public RefreshRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public RefreshRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
