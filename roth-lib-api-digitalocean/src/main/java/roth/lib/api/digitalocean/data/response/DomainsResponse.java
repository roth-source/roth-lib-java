package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Domain;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainsResponse implements Serializable
{
	@Property(name = "domains")
	protected LinkedList<Domain> domains;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public DomainsResponse()
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
	
	public DomainsResponse setDomains(LinkedList<Domain> domains)
	{
		this.domains = domains;
		return this;
	}
	
	public DomainsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
