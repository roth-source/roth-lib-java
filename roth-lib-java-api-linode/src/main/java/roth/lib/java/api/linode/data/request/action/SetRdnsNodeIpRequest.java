package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

@Entity
@SuppressWarnings("serial")
public class SetRdnsNodeIpRequest extends LinodeRequest
{
	@Property(name = "IPAddressID")
	protected Integer ipAddressId;
	
	@Property(name = "Hostname")
	protected String hostname;
	
	public SetRdnsNodeIpRequest(Integer ipAddressId, String hostname)
	{
		super();
		setIpAddressId(ipAddressId);
		setHostname(hostname);
	}
	
	public Integer getIpAddressId()
	{
		return ipAddressId;
	}
	
	public String getHostname()
	{
		return hostname;
	}
	
	public SetRdnsNodeIpRequest setIpAddressId(Integer ipAddressId)
	{
		this.ipAddressId = ipAddressId;
		return this;
	}
	
	public SetRdnsNodeIpRequest setHostname(String hostname)
	{
		this.hostname = hostname;
		return this;
	}
	
}
