package roth.lib.api.linode.data.request.create;

import roth.lib.api.linode.data.request.DomainRequest;
import roth.lib.api.linode.data.type.DomainType;

@SuppressWarnings("serial")
public class CreateDomainRequest extends DomainRequest
{
	
	public CreateDomainRequest(String domain, DomainType domainType, String soaEmail)
	{
		super();
		setDomain(domain);
		setType(domainType);
		setSoaEmail(soaEmail);
	}
	
}
