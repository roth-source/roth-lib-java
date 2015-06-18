package roth.lib.api.linode.data.request.update;

import roth.lib.api.linode.data.request.DomainRequest;
import roth.lib.annotation.Property;

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
