package roth.lib.api.digitalocean.domain;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Domain;
import roth.lib.api.digitalocean.model.Meta;

@SuppressWarnings("serial")
public class GetDomainsResponse implements Serializable
{
	@Property(name = "domains")
	protected LinkedList<Domain> domains;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetDomainsResponse()
	{
		
	}
	
	public LinkedList<Domain> getDomains()
	{
		return domains;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public GetDomainsResponse setDomains(LinkedList<Domain> domains)
	{
		this.domains = domains;
		return this;
	}
	
	public GetDomainsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
