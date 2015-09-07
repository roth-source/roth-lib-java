package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeIpResponse implements Serializable
{
	@Property(name = "IPAddressID")
	protected Integer ipAddressId;
	
	@Property(name = "IPAddress")
	protected String ipAddress;
	
	@Property(name = "HOSTNAME")
	protected String hostname;
	
	public NodeIpResponse()
	{
		
	}
	
	public Integer getIpAddressId()
	{
		return ipAddressId;
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public String getHostname()
	{
		return hostname;
	}
	
	public NodeIpResponse setIpAddressId(Integer ipAddressId)
	{
		this.ipAddressId = ipAddressId;
		return this;
	}
	
	public NodeIpResponse setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
		return this;
	}
	
	public NodeIpResponse setHostname(String hostname)
	{
		this.hostname = hostname;
		return this;
	}
	
}
