package roth.lib.api.digitalocean.domainrecord;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.DomainRecord;

@Entity
@SuppressWarnings("serial")
public class CreateDomainRecordResponse implements Serializable
{
	@Property(name = "domain_record")
	protected DomainRecord domainRecord;
	
	public CreateDomainRecordResponse()
	{
		
	}
	
	public DomainRecord getDomainRecord()
	{
		return domainRecord;
	}
	
	public CreateDomainRecordResponse setDomainRecord(DomainRecord domainRecord)
	{
		this.domainRecord = domainRecord;
		return this;
	}
	
}
