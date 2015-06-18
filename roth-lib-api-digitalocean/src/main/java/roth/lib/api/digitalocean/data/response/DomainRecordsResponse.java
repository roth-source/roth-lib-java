package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.DomainRecord;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainRecordsResponse implements Serializable
{
	@Property(name = "domain_records")
	protected LinkedList<DomainRecord> domainRecords;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public DomainRecordsResponse()
	{
		
	}
	
	public LinkedList<DomainRecord> getDomainRecords()
	{
		return domainRecords;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public DomainRecordsResponse setDomainRecords(LinkedList<DomainRecord> domainRecords)
	{
		this.domainRecords = domainRecords;
		return this;
	}
	
	public DomainRecordsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
