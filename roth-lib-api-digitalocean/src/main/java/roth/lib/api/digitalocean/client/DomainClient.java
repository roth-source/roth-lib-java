package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.DomainRequest;
import roth.lib.api.digitalocean.data.response.DomainResponse;
import roth.lib.api.digitalocean.data.response.DomainsResponse;

public class DomainClient extends DigitalOceanClient
{
	
	public DomainClient(String token)
	{
		this(token, false);
	}
	
	public DomainClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public DomainsResponse getDomains(int page)
	{
		return get(url(DOMAINS, page), DomainsResponse.class);
	}
	
	public DomainResponse getDomain(String domain)
	{
		return get(url(DOMAINS + domain), DomainResponse.class);
	}
	
	public DomainResponse createDomain(DomainRequest domainRequest)
	{
		return post(url(DOMAINS), domainRequest, DomainResponse.class);
	}
	
	public void deleteDomain(String domain)
	{
		delete(url(DOMAINS + domain));
	}
	
}
