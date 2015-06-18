package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.LinodeRequest;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class GetAvailPlanRequest extends LinodeRequest
{
	@Property(name = "PlanID")
	protected Integer planId;
	
	public GetAvailPlanRequest()
	{
		
	}
	
	public Integer getPlanId()
	{
		return planId;
	}
	
	public GetAvailPlanRequest setPlanId(Integer planId)
	{
		this.planId = planId;
		return this;
	}
	
}
