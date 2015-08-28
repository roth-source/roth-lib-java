package roth.lib.api.cloudflare.access;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.CloudFlareRequest;
import roth.lib.util.StringUtil;

@SuppressWarnings("serial")
public class CheckZonesRequest extends CloudFlareRequest
{
	@Property(name = "zones")
	protected String zones;
	
	public CheckZonesRequest(String...domains)
	{
		setDomains(domains);
	}
	
	public String getZones()
	{
		return zones;
	}
	
	public CheckZonesRequest setZones(String zones)
	{
		this.zones = zones;
		return this;
	}
	
	public CheckZonesRequest setDomains(String...domains)
	{
		this.zones = StringUtil.comma(domains);
		return this;
	}
	
	@Override
	public CheckZonesRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public CheckZonesRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public CheckZonesRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
