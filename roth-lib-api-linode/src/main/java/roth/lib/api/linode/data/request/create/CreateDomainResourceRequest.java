package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.DomainResourceRequest;
import roth.lib.api.linode.data.type.DomainResourceType;

@SuppressWarnings("serial")
public class CreateDomainResourceRequest extends DomainResourceRequest
{
	
	public CreateDomainResourceRequest(Integer domainId, DomainResourceType domainResourceType)
	{
		super();
		setDomainId(domainId);
		setType(domainResourceType);
	}
	
}
