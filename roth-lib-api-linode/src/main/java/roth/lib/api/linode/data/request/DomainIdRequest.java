package roth.lib.api.linode.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public abstract class DomainIdRequest extends LinodeRequest
{
	@Property(name = "DomainID")
	protected Integer domainId;
	
	public DomainIdRequest()
	{
		
	}
	
	public Integer getDomainId()
	{
		return domainId;
	}
	
	public DomainIdRequest setDomainId(Integer domainId)
	{
		this.domainId = domainId;
		return this;
	}
	
}
