package roth.lib.java.api.linode.data.request.get;

import roth.lib.java.api.linode.data.request.DomainResourceIdRequest;

@SuppressWarnings("serial")
public class GetDomainResourceRequest extends DomainResourceIdRequest
{

	public GetDomainResourceRequest(Integer domainId)
	{
		super(domainId);
	}
	
}
