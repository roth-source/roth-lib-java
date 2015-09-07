package roth.lib.api.digitalocean.domain;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Domain;

@Entity
@SuppressWarnings("serial")
public class CreateDomainResponse implements Serializable
{
	@Property(name = "domain")
	protected Domain domain;
	
	public CreateDomainResponse()
	{
		
	}
	
	public Domain getDomain()
	{
		return domain;
	}
	
	public CreateDomainResponse setDomain(Domain domain)
	{
		this.domain = domain;
		return this;
	}
	
}
