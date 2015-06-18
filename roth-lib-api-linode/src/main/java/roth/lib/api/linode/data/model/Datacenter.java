package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Datacenter implements Serializable
{
	@Property(name = "DATACENTERID")
	protected Integer datacenterId;
	
	@Property(name = "LOCATION")
	protected String location;
	
	@Property(name = "ABBR")
	protected String abbreviation;
	
	public Datacenter()
	{
		
	}
	
	public Integer getDatacenterId()
	{
		return datacenterId;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public String getAbbreviation()
	{
		return abbreviation;
	}
	
	public Datacenter setDatacenterId(Integer datacenterId)
	{
		this.datacenterId = datacenterId;
		return this;
	}
	
	public Datacenter setLocation(String location)
	{
		this.location = location;
		return this;
	}
	
	public Datacenter setAbbreviation(String abbreviation)
	{
		this.abbreviation = abbreviation;
		return this;
	}
	
}
