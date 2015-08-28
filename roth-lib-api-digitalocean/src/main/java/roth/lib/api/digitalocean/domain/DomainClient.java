package roth.lib.api.digitalocean.domain;

import roth.lib.api.digitalocean.DigitalOceanClient;

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
	
	public GetDomainsResponse getDomains(int page)
	{
		return get(url(DOMAINS, page), GetDomainsResponse.class);
	}
	
	public GetDomainResponse getDomain(String domain)
	{
		return get(url(DOMAINS + domain), GetDomainResponse.class);
	}
	
	public CreateDomainResponse createDomain(CreateDomainRequest request)
	{
		return post(url(DOMAINS), request, CreateDomainResponse.class);
	}
	
	public void deleteDomain(String domain)
	{
		delete(url(DOMAINS + domain));
	}
	
}
