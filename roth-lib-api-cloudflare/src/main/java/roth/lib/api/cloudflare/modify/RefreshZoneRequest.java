package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.CloudFlareRequest;

@SuppressWarnings("serial")
public class RefreshZoneRequest extends CloudFlareRequest
{
	@Property(name = "zid")
	protected Integer zoneId;
	
	public RefreshZoneRequest(int zoneId)
	{
		this.zoneId = zoneId;
	}
	
	public Integer getZoneId()
	{
		return zoneId;
	}
	
	public RefreshZoneRequest setZoneId(Integer zoneId)
	{
		this.zoneId = zoneId;
		return this;
	}
	
	@Override
	public RefreshZoneRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public RefreshZoneRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public RefreshZoneRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
