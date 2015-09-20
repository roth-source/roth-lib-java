package roth.lib.java.api.cloudflare.access;

import java.io.Serializable;
import java.util.LinkedHashMap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class CheckZonesResponse implements Serializable
{
	@Property(name = "zones")
	protected LinkedHashMap<String, Integer> zones = new LinkedHashMap<String, Integer>();
	
	public CheckZonesResponse()
	{
		
	}
	
	public LinkedHashMap<String, Integer> getZones()
	{
		return zones;
	}
	
}
