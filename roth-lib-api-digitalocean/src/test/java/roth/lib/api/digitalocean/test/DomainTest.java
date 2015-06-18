package roth.lib.api.digitalocean.test;

import roth.lib.api.digitalocean.data.request.DomainRequest;

public class DomainTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getDomains();
		//getDomain();
		//createDomain();
		//deleteDomain();
	}
	
	public static void getDomains()
	{
		clientFactory.getDomainClient().getDomains(1);
	}
	
	public static void getDomain()
	{
		clientFactory.getDomainClient().getDomain(domain);
	}
	
	public static void createDomain()
	{
		clientFactory.getDomainClient().createDomain(new DomainRequest("mytemporarydomain.com", "162.243.219.71"));
	}
	
	public static void deleteDomain()
	{
		clientFactory.getDomainClient().deleteDomain("mytemporarydomain.com");
	}
	
}
