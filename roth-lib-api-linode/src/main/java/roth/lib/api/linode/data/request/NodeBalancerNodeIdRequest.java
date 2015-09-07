package roth.lib.api.linode.data.request;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

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
