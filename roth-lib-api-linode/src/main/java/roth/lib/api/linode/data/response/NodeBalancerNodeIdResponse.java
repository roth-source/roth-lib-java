package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class NodeBalancerNodeIdResponse implements Serializable
{
	@Property(name = "NodeID")
	protected Integer nodeId;
	
	public NodeBalancerNodeIdResponse()
	{
		
	}
	
	public Integer getNodeId()
	{
		return nodeId;
	}
	
	public NodeBalancerNodeIdResponse setNodeId(Integer nodeId)
	{
		this.nodeId = nodeId;
		return this;
	}
	
}
