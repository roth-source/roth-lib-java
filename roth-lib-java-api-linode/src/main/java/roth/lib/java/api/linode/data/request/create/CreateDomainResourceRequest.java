package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.api.linode.data.request.DomainResourceRequest;
import roth.lib.java.api.linode.data.type.DomainResourceType;

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
