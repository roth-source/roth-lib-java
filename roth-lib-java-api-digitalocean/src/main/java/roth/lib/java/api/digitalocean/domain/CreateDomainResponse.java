package roth.lib.java.api.digitalocean.domain;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Domain;

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
