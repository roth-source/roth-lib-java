package roth.lib.api.digitalocean.domain;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Domain;

@SuppressWarnings("serial")
public class GetDomainResponse implements Serializable
{
	@Property(name = "domain")
	protected Domain domain;
	
	public GetDomainResponse()
	{
		
	}
	
	public Domain getDomain()
	{
		return domain;
	}
	
	public GetDomainResponse setDomain(Domain domain)
	{
		this.domain = domain;
		return this;
	}
	
}
