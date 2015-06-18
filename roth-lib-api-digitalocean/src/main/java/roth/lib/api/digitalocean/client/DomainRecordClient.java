package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.DomainRecordRequest;
import roth.lib.api.digitalocean.data.response.DomainRecordResponse;
import roth.lib.api.digitalocean.data.response.DomainRecordsResponse;

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
	
	public DomainRecordsResponse getDomainRecords(String domain, int page)
	{
		return get(url(DOMAINS + domain + RECORDS, page), DomainRecordsResponse.class);
	}
	
	public DomainRecordResponse getDomainRecord(String domain, int id)
	{
		return get(url(DOMAINS + domain + RECORDS + id), DomainRecordResponse.class);
	}
	
	public DomainRecordResponse createDomainRecord(String domain, DomainRecordRequest domainRecordRequest)
	{
		return post(url(DOMAINS + domain + RECORDS), domainRecordRequest, DomainRecordResponse.class);
	}
	
	public DomainRecordResponse updateDomainRecord(String domain, int id, DomainRecordRequest domainRecordRequest)
	{
		return put(url(DOMAINS + domain + RECORDS + id), domainRecordRequest, DomainRecordResponse.class);
	}
	
	public void deleteDomainRecord(String domain, int id)
	{
		delete(url(DOMAINS + domain + RECORDS + id));
	}
	
}
