package roth.lib.api.digitalocean.domainrecord;

import roth.lib.api.digitalocean.DigitalOceanClient;

public class DomainRecordClient extends DigitalOceanClient
{
	
	public DomainRecordClient(String token)
	{
		this(token, false);
	}
	
	public DomainRecordClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public GetDomainRecordsResponse getDomainRecords(String domain, int page)
	{
		return get(url(DOMAINS + domain + RECORDS, page), GetDomainRecordsResponse.class);
	}
	
	public GetDomainRecordResponse getDomainRecord(String domain, int id)
	{
		return get(url(DOMAINS + domain + RECORDS + id), GetDomainRecordResponse.class);
	}
	
	public CreateDomainRecordResponse createDomainRecord(String domain, CreateDomainRecordRequest request)
	{
		return post(url(DOMAINS + domain + RECORDS), request, GetDomainRecordResponse.class);
	}
	
	public UpdateDomainRecordResponse updateDomainRecord(String domain, int id, UpdateDomainRecordRequest request)
	{
		return put(url(DOMAINS + domain + RECORDS + id), request, GetDomainRecordResponse.class);
	}
	
	public void deleteDomainRecord(String domain, int id)
	{
		delete(url(DOMAINS + domain + RECORDS + id));
	}
	
}
