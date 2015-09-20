package roth.lib.java.api.linode.data.request.delete;

import roth.lib.java.api.linode.data.request.NodeBalancerIdRequest;

@SuppressWarnings("serial")
public class DeleteNodeBalancerRequest extends NodeBalancerIdRequest
{
	
	public DeleteNodeBalancerRequest(Integer nodeBalancerId)
	{
		super();
		setNodeBalancerId(nodeBalancerId);
	}
	
}
