package roth.lib.java.api.linode.data.response;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class DomainIdResponse implements Serializable
{
	@Property(name = "DomainID")
	protected Integer domainId;
	
	public DomainIdResponse()
	{
		
	}
	
	public Integer getDomainId()
	{
		return domainId;
	}
	
	public DomainIdResponse setDomainId(Integer domainId)
	{
		this.domainId = domainId;
		return this;
	}
	
}
