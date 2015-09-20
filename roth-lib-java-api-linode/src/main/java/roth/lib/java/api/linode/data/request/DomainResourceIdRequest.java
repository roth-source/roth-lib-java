package roth.lib.java.api.linode.data.request;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public abstract class DomainResourceIdRequest extends DomainIdRequest
{
	@Property(name = "ResourceID")
	protected Integer resourceId;
	
	public DomainResourceIdRequest(Integer domainId)
	{
		this.domainId = domainId;
	}
	
	public Integer getResourceId()
	{
		return resourceId;
	}
	
	public DomainResourceIdRequest setResourceId(Integer resourceId)
	{
		this.resourceId = resourceId;
		return this;
	}
	
	@Override
	public DomainResourceIdRequest setDomainId(Integer domainId)
	{
		super.setDomainId(domainId);
		return this;
	}
	
}
