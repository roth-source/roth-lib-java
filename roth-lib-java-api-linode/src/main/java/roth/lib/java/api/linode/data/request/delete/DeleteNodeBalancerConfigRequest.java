package roth.lib.java.api.linode.data.request.delete;

import roth.lib.java.api.linode.data.request.NodeBalancerConfigIdRequest;

@SuppressWarnings("serial")
public class DeleteNodeBalancerConfigRequest extends NodeBalancerConfigIdRequest
{
	
	public DeleteNodeBalancerConfigRequest(Integer nodeBalancerId, Integer configId)
	{
		super();
		setNodeBalancerId(nodeBalancerId);
		setConfigId(configId);
	}
	
}
