package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeBalancerIdResponse implements Serializable
{
	@Property(name = "NodeBalancerID")
	protected Integer nodeBalancerId;
	
	public NodeBalancerIdResponse()
	{
		
	}
	
	public Integer getNodeBalancerId()
	{
		return nodeBalancerId;
	}
	
	public NodeBalancerIdResponse setNodeBalancerId(Integer nodeBalancerId)
	{
		this.nodeBalancerId = nodeBalancerId;
		return this;
	}
	
}
