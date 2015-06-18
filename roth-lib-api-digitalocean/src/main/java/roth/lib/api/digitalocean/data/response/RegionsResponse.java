package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Region;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RegionsResponse implements Serializable
{
	@Property(name = "regions")
	protected LinkedList<Region> regions;
	
	public RegionsResponse()
	{
		
	}
	
	public LinkedList<Region> getRegions()
	{
		return regions;
	}
	
	public RegionsResponse setRegions(LinkedList<Region> regions)
	{
		this.regions = regions;
		return this;
	}
	
}
