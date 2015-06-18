package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;
import java.util.Calendar;

import roth.lib.api.cloudflare.data.model.Zone;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ZoneResponse implements Serializable
{
	@Property(name = "zone")
	protected Zone zone;
	
	@Property(name = "expires_on")
	protected Calendar expiresOn;
	
	@Property(name = "fpurge_ts")
	protected Calendar fpurgeTs;
	
	public ZoneResponse()
	{
		
	}
	
	public Zone getZone()
	{
		return zone;
	}
	
	public Calendar getExpiresOn()
	{
		return expiresOn;
	}
	
	public Calendar getFpurgeTs()
	{
		return fpurgeTs;
	}
	
}
