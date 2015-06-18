package roth.lib.api.linode.data.request.delete;

import roth.lib.api.linode.data.request.DomainResourceIdRequest;

@SuppressWarnings("serial")
public class DeleteDomainResourceRequest extends DomainResourceIdRequest
{
	
	public DeleteDomainResourceRequest(Integer domainId, Integer resourceId)
	{
		super(domainId);
		setResourceId(resourceId);
	}
	
}
