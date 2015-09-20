package roth.lib.java.api.digitalocean.domainrecord;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.DomainRecord;
import roth.lib.java.api.digitalocean.model.Meta;

@Entity
@SuppressWarnings("serial")
public class GetDomainRecordsResponse implements Serializable
{
	@Property(name = "domain_records")
	protected LinkedList<DomainRecord> domainRecords;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetDomainRecordsResponse()
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
	
	public GetDomainRecordsResponse setDomainRecords(LinkedList<DomainRecord> domainRecords)
	{
		this.domainRecords = domainRecords;
		return this;
	}
	
	public GetDomainRecordsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
