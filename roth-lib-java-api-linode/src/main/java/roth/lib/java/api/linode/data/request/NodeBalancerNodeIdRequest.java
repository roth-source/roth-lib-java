package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class NodeBalancerNodeIdRequest extends LinodeRequest
{
	@Property(name = "NodeID")
	protected Integer nodeId;
	
	public NodeBalancerNodeIdRequest()
	{
		
	}
	
	public Integer getNodeId()
	{
		return nodeId;
	}
	
	public NodeBalancerNodeIdRequest setNodeId(Integer nodeId)
	{
		this.nodeId = nodeId;
		return this;
	}
	
}
