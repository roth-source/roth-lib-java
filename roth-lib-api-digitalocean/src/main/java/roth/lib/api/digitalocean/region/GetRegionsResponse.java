package roth.lib.api.digitalocean.region;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Region;

@Entity
@SuppressWarnings("serial")
public class GetRegionsResponse implements Serializable
{
	@Property(name = "regions")
	protected LinkedList<Region> regions;
	
	public GetRegionsResponse()
	{
		
	}
	
	public LinkedList<Region> getRegions()
	{
		return regions;
	}
	
	public GetRegionsResponse setRegions(LinkedList<Region> regions)
	{
		this.regions = regions;
		return this;
	}
	
}
