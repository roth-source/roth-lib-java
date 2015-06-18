package roth.lib.api.linode.data.request.delete;

import roth.lib.api.linode.data.request.NodeBalancerIdRequest;

@SuppressWarnings("serial")
public class DeleteNodeBalancerRequest extends NodeBalancerIdRequest
{
	
	public DeleteNodeBalancerRequest(Integer nodeBalancerId)
	{
		super();
		setNodeBalancerId(nodeBalancerId);
	}
	
}
