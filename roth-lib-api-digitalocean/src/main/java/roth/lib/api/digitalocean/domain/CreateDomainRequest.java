package roth.lib.api.digitalocean.domain;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class CreateDomainRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "ip_address")
	protected String ipAddress;
	
	public CreateDomainRequest(String name, String ipAddress)
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
	
	public CreateDomainRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public CreateDomainRequest setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
		return this;
	}
	
}
