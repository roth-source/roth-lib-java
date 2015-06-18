package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;

import roth.lib.api.cloudflare.data.model.Zones;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ZonesResponse implements Serializable
{
	@Property(name = "zones")
	protected Zones zones;
	
	public ZonesResponse()
	{
		
	}
	
	public Zones getZones()
	{
		return zones;
	}
	
}
