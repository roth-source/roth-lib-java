package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

@Entity
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
