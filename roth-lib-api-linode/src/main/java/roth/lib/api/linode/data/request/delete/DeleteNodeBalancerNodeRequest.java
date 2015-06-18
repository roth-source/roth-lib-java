package roth.lib.api.linode.data.request.delete;

import roth.lib.api.linode.data.request.NodeBalancerNodeIdRequest;

@SuppressWarnings("serial")
public class DeleteNodeBalancerNodeRequest extends NodeBalancerNodeIdRequest
{
	
	public DeleteNodeBalancerNodeRequest(Integer nodeId)
	{
		super();
		setNodeId(nodeId);
	}
	
}
