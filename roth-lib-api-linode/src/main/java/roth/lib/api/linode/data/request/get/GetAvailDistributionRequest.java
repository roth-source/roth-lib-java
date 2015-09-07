package roth.lib.api.linode.data.request.get;

import roth.lib.api.linode.data.request.LinodeRequest;
import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class GetAvailDistributionRequest extends LinodeRequest
{
	@Property(name = "DistributionID")
	protected Integer distributionId;
	
	public GetAvailDistributionRequest()
	{
		
	}
	
	public Integer getDistributionId()
	{
		return distributionId;
	}
	
	public GetAvailDistributionRequest setDistributionId(Integer distributionId)
	{
		this.distributionId = distributionId;
		return this;
	}
	
}
