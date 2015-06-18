package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.NodeBalancerNodeRequest;
import roth.lib.annotation.Property;

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
