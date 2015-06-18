package roth.lib.api.digitalocean.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Domain implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "ttl")
	protected Integer ttl;
	
	@Property(name = "zone_file")
	protected String zoneFile;
	
	@Property(name = "ip_address")
	protected String ipAddress;
	
	public Domain()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getTtl()
	{
		return ttl;
	}
	
	public String getZoneFile()
	{
		return zoneFile;
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public Domain setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Domain setTtl(Integer ttl)
	{
		this.ttl = ttl;
		return this;
	}
	
	public Domain setZoneFile(String zoneFile)
	{
		this.zoneFile = zoneFile;
		return this;
	}
	
	public Domain setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
		return this;
	}
	
}
