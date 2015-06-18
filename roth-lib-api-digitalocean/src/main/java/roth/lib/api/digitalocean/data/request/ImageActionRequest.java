package roth.lib.api.digitalocean.data.request;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.type.RegionType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ImageActionRequest implements Serializable
{
	@Property(name = "type")
	protected String type;
	
	@Property(name = "region")
	protected String region;
	
	public ImageActionRequest()
	{
		
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public ImageActionRequest setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public ImageActionRequest setRegion(String region)
	{
		this.region = region;
		return this;
	}
	
	public ImageActionRequest setRegionType(RegionType regionType)
	{
		setRegion(regionType.get());
		return this;
	}
	
}
