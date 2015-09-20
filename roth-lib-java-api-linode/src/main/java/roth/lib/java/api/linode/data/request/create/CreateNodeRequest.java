package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.api.linode.data.request.NodeRequest;

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
