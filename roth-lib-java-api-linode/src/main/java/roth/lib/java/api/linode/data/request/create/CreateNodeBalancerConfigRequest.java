package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.api.linode.data.request.NodeBalancerConfigRequest;

@SuppressWarnings("serial")
public class CreateNodeBalancerConfigRequest extends NodeBalancerConfigRequest
{
	
	public CreateNodeBalancerConfigRequest(Integer nodeBalancerId)
	{
		super();
		setNodeBalancerId(nodeBalancerId);
	}
	
}
