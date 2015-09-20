package roth.lib.java.api.linode.test;

import roth.lib.java.api.linode.data.request.create.CreateDomainRequest;
import roth.lib.java.api.linode.data.request.create.CreateDomainResourceRequest;
import roth.lib.java.api.linode.data.request.delete.DeleteDomainRequest;
import roth.lib.java.api.linode.data.request.delete.DeleteDomainResourceRequest;
import roth.lib.java.api.linode.data.request.get.GetDomainResourceRequest;
import roth.lib.java.api.linode.data.request.update.UpdateDomainRequest;
import roth.lib.java.api.linode.data.request.update.UpdateDomainResourceRequest;
import roth.lib.java.api.linode.data.type.DomainResourceType;
import roth.lib.java.api.linode.data.type.DomainType;

public class DomainTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getDomains();
		//createDomain();
		//updateDomain();
		//deleteDomain();
		//getDomainResource();
		//createDomainResource();
		//updateDomainResource();
		//deleteDomainResource();
	}
	
	public static void getDomains()
	{
		clientFactory.getDomainClient().getDomains();
	}
	
	public static void createDomain()
	{
		clientFactory.getDomainClient().createDomain(new CreateDomainRequest("roth.am", DomainType.MASTER, "admin@roth.am"));
	}
	
	public static void updateDomain()
	{
		clientFactory.getDomainClient().updateDomain((UpdateDomainRequest) new UpdateDomainRequest(590627).setSoaEmail("test@roth.am"));
	}
	
	public static void deleteDomain()
	{
		clientFactory.getDomainClient().deleteDomain(new DeleteDomainRequest(590627));
	}
	
	public static void getDomainResource()
	{
		clientFactory.getDomainClient().getDomainResource(new GetDomainResourceRequest(590632));
	}
	
	public static void createDomainResource()
	{
		clientFactory.getDomainClient().createDomainResource((CreateDomainResourceRequest) new CreateDomainResourceRequest(590632, DomainResourceType.A).setName("test").setTarget("162.243.219.71"));
	}
	
	public static void updateDomainResource()
	{
		clientFactory.getDomainClient().updateDomainResource((UpdateDomainResourceRequest) new UpdateDomainResourceRequest(590632, 4187839).setName("test2"));
	}
	
	public static void deleteDomainResource()
	{
		clientFactory.getDomainClient().deleteDomainResource(new DeleteDomainResourceRequest(590632, 4187839));
	}
	
}
