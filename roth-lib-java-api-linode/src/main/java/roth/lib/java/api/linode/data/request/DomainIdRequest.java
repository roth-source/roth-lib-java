package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
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
