package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

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
