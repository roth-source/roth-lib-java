package roth.lib.api.digitalocean.test;

import roth.lib.api.digitalocean.domainrecord.CreateDomainRecordRequest;
import roth.lib.api.digitalocean.domainrecord.UpdateDomainRecordRequest;
import roth.lib.api.digitalocean.type.DomainRecordType;

public class DomainRecordTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getDomainRecords();
		//getDomainRecord();
		//createDomainRecord();
		//updateDomainRecord();
		//deleteDomainRecord();
	}
	
	public static void getDomainRecords()
	{
		clientFactory.getDomainRecordClient().getDomainRecords(domain, 1);
	}
	
	public static void getDomainRecord()
	{
		clientFactory.getDomainRecordClient().getDomainRecord(domain, 5250987);
	}
	
	public static void createDomainRecord()
	{
		clientFactory.getDomainRecordClient().createDomainRecord(domain, new CreateDomainRecordRequest(DomainRecordType.A, "test", "162.243.219.71"));
	}
	
	public static void updateDomainRecord()
	{
		clientFactory.getDomainRecordClient().updateDomainRecord(domain, 5250987, new UpdateDomainRecordRequest(DomainRecordType.A, "test", "162.243.219.71"));
	}
	
	public static void deleteDomainRecord()
	{
		clientFactory.getDomainRecordClient().deleteDomainRecord(domain, 5250987);
	}
	
}
