package roth.lib.api.linode.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class NodeBalancerNode implements Serializable
{
	@Property(name = "NODEBALANCERID")
	protected Integer nodeBalancerId;
	
	@Property(name = "CONFIGID")
	protected Integer configId;
	
	@Property(name = "WEIGHT")
	protected Integer weight;
	
	@Property(name = "ADDRESS")
	protected String address;
	
	@Property(name = "LABEL")
	protected String label;
	
	@Property(name = "MODE")
	protected String mode;
	
	@Property(name = "STATUS")
	protected String status;
	
	public NodeBalancerNode()
	{
		
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public Integer getConfigId()
	{
		return configId;
	}
	
	public Integer getWeight()
	{
		return weight;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getMode()
	{
		return mode;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public NodeBalancerNode setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
	public NodeBalancerNode setConfigId(Integer configId)
	{
		this.configId = configId;
		return this;
	}
	
	public NodeBalancerNode setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
	public NodeBalancerNode setAddress(String address)
	{
		this.address = address;
		return this;
	}
	
	public NodeBalancerNode setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeBalancerNode setMode(String mode)
	{
		this.mode = mode;
		return this;
	}
	
	public NodeBalancerNode setStatus(String status)
	{
		this.status = status;
		return this;
	}
	
}
