package roth.lib.api.digitalocean.data.request;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "ip_address")
	protected String ipAddress;
	
	public DomainRequest(String name, String ipAddress)
	{
		this.name = name;
		this.ipAddress = ipAddress;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public DomainRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DomainRequest setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
		return this;
	}
	
}
