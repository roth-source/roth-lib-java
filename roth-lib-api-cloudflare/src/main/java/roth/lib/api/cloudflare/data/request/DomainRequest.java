package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainRequest extends CloudFlareRequest
{
	@Property(name = "z")
	protected String domain;
	
	public DomainRequest(String domain)
	{
		super();
		this.domain = domain;
	}
	
	public String getDomain()
	{
		return domain;
	}
	
	public DomainRequest setDomain(String domain)
	{
		this.domain = domain;
		return this;
	}
	
}
