package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.model.Domain;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainResponse implements Serializable
{
	@Property(name = "domain")
	protected Domain domain;
	
	public DomainResponse()
	{
		
	}
	
	public Domain getDomain()
	{
		return domain;
	}
	
	public DomainResponse setDomain(Domain domain)
	{
		this.domain = domain;
		return this;
	}
	
}
