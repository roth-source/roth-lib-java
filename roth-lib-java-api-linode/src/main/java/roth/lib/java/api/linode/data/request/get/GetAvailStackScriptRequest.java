package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.LinodeRequest;

@Entity
@SuppressWarnings("serial")
public class GetAvailStackScriptRequest extends LinodeRequest
{
	@Property(name = "DistributionID")
	protected Integer distributionId;
	
	@Property(name = "DistributionVendor")
	protected String distributionVendor;
	
	@Property(name = "keywords")
	protected String keywords;
	
	public GetAvailStackScriptRequest()
	{
		
	}
	
	public Integer getDistributionId()
	{
		return distributionId;
	}
	
	public String getDistributionVendor()
	{
		return distributionVendor;
	}
	
	public String getKeywords()
	{
		return keywords;
	}
	
	public GetAvailStackScriptRequest setDistributionId(Integer distributionId)
	{
		this.distributionId = distributionId;
		return this;
	}
	
	public GetAvailStackScriptRequest setDistributionVendor(String distributionVendor)
	{
		this.distributionVendor = distributionVendor;
		return this;
	}
	
	public GetAvailStackScriptRequest setKeywords(String keywords)
	{
		this.keywords = keywords;
		return this;
	}
	
}
