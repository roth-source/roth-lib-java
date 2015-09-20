package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

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
