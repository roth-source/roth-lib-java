package roth.lib.java.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.model.Zones;

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
