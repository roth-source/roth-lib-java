package roth.lib.api.digitalocean.domainrecord;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.DomainRecord;

@SuppressWarnings("serial")
public class UpdateDomainRecordResponse implements Serializable
{
	@Property(name = "domain_record")
	protected DomainRecord domainRecord;
	
	public UpdateDomainRecordResponse()
	{
		
	}
	
	public DomainRecord getDomainRecord()
	{
		return domainRecord;
	}
	
	public UpdateDomainRecordResponse setDomainRecord(DomainRecord domainRecord)
	{
		this.domainRecord = domainRecord;
		return this;
	}
	
}
