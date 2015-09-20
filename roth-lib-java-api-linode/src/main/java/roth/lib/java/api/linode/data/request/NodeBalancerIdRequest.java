package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeBalancerIdRequest extends LinodeRequest
{
	@Property(name = "NodeBalancerID")
	protected Integer nodeBalancerId;
	
	public NodeBalancerIdRequest()
	{
		
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public NodeBalancerIdRequest setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
}
