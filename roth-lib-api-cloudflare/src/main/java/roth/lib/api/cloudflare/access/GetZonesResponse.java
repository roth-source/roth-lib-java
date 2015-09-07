package roth.lib.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.Zones;

@Entity
@SuppressWarnings("serial")
public class GetZonesResponse implements Serializable
{
	@Property(name = "zones")
	protected Zones zones;
	
	public GetZonesResponse()
	{
		
	}
	
	public Zones getZones()
	{
		return zones;
	}
	
}
