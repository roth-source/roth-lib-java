package roth.lib.java.api.linode.data.request.action;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.NodeIdRequest;

@Entity
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
