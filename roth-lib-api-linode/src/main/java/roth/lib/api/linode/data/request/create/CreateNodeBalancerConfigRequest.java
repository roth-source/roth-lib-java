package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.NodeBalancerConfigRequest;

@SuppressWarnings("serial")
public class CreateNodeBalancerConfigRequest extends NodeBalancerConfigRequest
{
	
	public CreateNodeBalancerConfigRequest(Integer nodeBalancerId)
	{
		super();
		setNodeBalancerId(nodeBalancerId);
	}
	
}
