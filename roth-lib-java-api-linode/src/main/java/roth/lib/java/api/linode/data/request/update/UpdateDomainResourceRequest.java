package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.DomainResourceRequest;

@Entity
@SuppressWarnings("serial")
public class UpdateDomainResourceRequest extends DomainResourceRequest
{
	@Property(name = "ResourceID")
	protected Integer resourceId;
	
	public UpdateDomainResourceRequest(Integer domainId, Integer resourceId)
	{
		super();
		setDomainId(domainId);
		setResourceId(resourceId);
	}
	
	public Integer getResourceId()
	{
		return resourceId;
	}
	
	public UpdateDomainResourceRequest setResourceId(Integer resourceId)
	{
		this.resourceId = resourceId;
		return this;
	}
	
}
