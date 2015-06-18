package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.NodeRequest;

@SuppressWarnings("serial")
public class CreateNodeRequest extends NodeRequest
{
	
	public CreateNodeRequest(Integer datacenterId, Integer planId)
	{
		super();
		setDatacenterId(datacenterId);
		setPlanId(planId);
	}
	
}
