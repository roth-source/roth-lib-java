package roth.lib.java.api.linode.data.request.delete;

import roth.lib.java.api.linode.data.request.DomainIdRequest;

@SuppressWarnings("serial")
public class DeleteDomainRequest extends DomainIdRequest
{
	
	public DeleteDomainRequest(Integer domainId)
	{
		super();
		setDomainId(domainId);
	}
	
}
