package roth.lib.java.api.linode.data.request.update;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.request.DomainRequest;

@Entity
@SuppressWarnings("serial")
public class UpdateDomainRequest extends DomainRequest
{
	@Property(name = "DomainID")
	protected Integer domainId;
	
	public UpdateDomainRequest(Integer domainId)
	{
		super();
		setDomainId(domainId);
	}
	
	public Integer getDomainId()
	{
		return domainId;
	}
	
	public UpdateDomainRequest setDomainId(Integer domainId)
	{
		this.domainId = domainId;
		return this;
	}
	
}
