package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.model.DomainRecord;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DomainRecordResponse implements Serializable
{
	@Property(name = "domain_record")
	protected DomainRecord domainRecord;
	
	public DomainRecordResponse()
	{
		
	}
	
	public DomainRecord getDomainRecord()
	{
		return domainRecord;
	}
	
	public DomainRecordResponse setDomainRecord(DomainRecord domainRecord)
	{
		this.domainRecord = domainRecord;
		return this;
	}
	
}
