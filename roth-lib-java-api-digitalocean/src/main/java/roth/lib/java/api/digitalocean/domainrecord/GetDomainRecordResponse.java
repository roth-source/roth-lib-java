package roth.lib.java.api.digitalocean.domainrecord;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.DomainRecord;

@Entity
@SuppressWarnings("serial")
public class GetDomainRecordResponse implements Serializable
{
	@Property(name = "domain_record")
	protected DomainRecord domainRecord;
	
	public GetDomainRecordResponse()
	{
		
	}
	
	public DomainRecord getDomainRecord()
	{
		return domainRecord;
	}
	
	public GetDomainRecordResponse setDomainRecord(DomainRecord domainRecord)
	{
		this.domainRecord = domainRecord;
		return this;
	}
	
}
