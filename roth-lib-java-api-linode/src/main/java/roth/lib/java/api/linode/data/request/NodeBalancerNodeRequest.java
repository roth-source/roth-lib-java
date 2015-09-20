package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeBalancerNodeRequest extends LinodeRequest
{
	@Property(name = "Label")
	protected String label;
	
	@Property(name = "Address")
	protected String address;
	
	@Property(name = "Weight")
	protected Integer weight;
	
	@Property(name = "Mode")
	protected String mode;
	
	public NodeBalancerNodeRequest()
	{
		
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public Integer getWeight()
	{
		return weight;
	}
	
	public String getMode()
	{
		return mode;
	}
	
	public NodeBalancerNodeRequest setLabel(String label)
	{
		this.label = label;
		return this;
	}
	
	public NodeBalancerNodeRequest setAddress(String address)
	{
		this.address = address;
		return this;
	}
	
	public NodeBalancerNodeRequest setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
	public NodeBalancerNodeRequest setMode(String mode)
	{
		this.mode = mode;
		return this;
	}
	
}
