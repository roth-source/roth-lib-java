package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeBalancerNodeRequest;

@Entity
@SuppressWarnings("serial")
public class UpdateNodeBalancerNodeRequest extends NodeBalancerNodeRequest
{
	@Property(name = "NodeID")
	protected Integer nodeId;
	
	public UpdateNodeBalancerNodeRequest(Integer nodeId)
	{
		super();
		setNodeId(nodeId);
	}
	
	public Integer getNodeId()
	{
		return nodeId;
	}
	
	public UpdateNodeBalancerNodeRequest setNodeId(Integer nodeId)
	{
		this.nodeId = nodeId;
		return this;
	}
	
}
