package roth.lib.api.cloudflare;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
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
