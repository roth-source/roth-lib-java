package roth.lib.api.linode.data.request.action;

import roth.lib.api.linode.data.request.NodeIdRequest;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ResizeNodeRequest extends NodeIdRequest
{
	@Property(name = "PlanID")
	protected Integer planId;
	
	public ResizeNodeRequest(Integer linodeId, Integer planId)
	{
		super();
		setLinodeId(linodeId);
		setPlanId(planId);
	}
	
	public Integer getPlanId()
	{
		return planId;
	}
	
	public ResizeNodeRequest setPlanId(Integer planId)
	{
		this.planId = planId;
		return this;
	}
	
}
