package roth.lib.api.digitalocean.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Network implements Serializable
{
	@Property(name = "ip_address")
	protected String ipAddress;
	
	@Property(name = "netmask")
	protected String netmask;
	
	@Property(name = "gateway")
	protected String gateway;
	
	@Property(name = "type")
	protected String type;
	
	public Network()
	{
		
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public String getNetmask()
	{
		return netmask;
	}
	
	public String getGateway()
	{
		return gateway;
	}
	
	public String getType()
	{
		return type;
	}
	
	public Network setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
		return this;
	}
	
	public Network setNetmask(String netmask)
	{
		this.netmask = netmask;
		return this;
	}
	
	public Network setGateway(String gateway)
	{
		this.gateway = gateway;
		return this;
	}
	
	public Network setType(String type)
	{
		this.type = type;
		return this;
	}
	
}
