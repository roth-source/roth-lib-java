package roth.lib.api.linode.data.response;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainResourceIdResponse implements Serializable
{
	@Property(name = "ResourceID")
	protected Integer resourceId;
	
	public DomainResourceIdResponse()
	{
		
	}
	
	public Integer getResourceId()
	{
		return resourceId;
	}
	
	public DomainResourceIdResponse setResourceId(Integer resourceId)
	{
		this.resourceId = resourceId;
		return this;
	}
	
}
