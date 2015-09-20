package roth.lib.java.api.linode.data.request.create;

import roth.lib.java.api.linode.data.request.DomainRequest;
import roth.lib.java.api.linode.data.type.DomainType;

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
