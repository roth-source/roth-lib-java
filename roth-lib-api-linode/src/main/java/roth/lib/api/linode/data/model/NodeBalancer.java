package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeBalancer implements Serializable
{
	@Property(name = "NODEBALANCERID")
	protected Integer nodeBalancerId;
	
	@Property(name = "DATACENTERID")
	protected Integer datacenterId;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "HOSTNAME")
	protected String hostname;
	
	@Property(name = "ADDRESS4")
	protected String address4;
	
	@Property(name = "ADDRESS6")
	protected String address6;
	
	@Property(name = "CLIENTCONNTHROTTLE")
	protected Integer clientConnThrottle;
	
	public NodeBalancer()
	{
		
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public Integer getDatacenterId()
	{
		return datacenterId;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getHostname()
	{
		return hostname;
	}
	
	public String getAddress4()
	{
		return address4;
	}
	
	public String getAddress6()
	{
		return address6;
	}
	
	public Integer getClientConnThrottle()
	{
		return clientConnThrottle;
	}
	
	public NodeBalancer setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
	public NodeBalancer setDatacenterId(Integer datacenterId)
	{
		this.datacenterId = datacenterId;
		return this;
	}
	
	public NodeBalancer setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeBalancer setHostname(String hostname)
	{
		this.hostname = hostname;
		return this;
	}
	
	public NodeBalancer setAddress4(String address4)
	{
		this.address4 = address4;
		return this;
	}
	
	public NodeBalancer setAddress6(String address6)
	{
		this.address6 = address6;
		return this;
	}
	
	public NodeBalancer setClientConnThrottle(Integer clientConnThrottle)
	{
		this.clientConnThrottle = clientConnThrottle;
		return this;
	}
	
}
