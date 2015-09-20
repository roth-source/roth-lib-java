package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
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
