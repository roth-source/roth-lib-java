package roth.lib.api.cloudflare.test;

import roth.lib.api.cloudflare.data.request.CreateRecordRequest;
import roth.lib.api.cloudflare.data.request.DeleteRecordRequest;
import roth.lib.api.cloudflare.data.request.UpdateRecordRequest;
import roth.lib.api.cloudflare.data.type.RecordType;


public class RecordTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//createRecord();
		//updateRecord();
		//deleteRecord();
		//createRoutedRecord();
	}
	
	public static void createRecord()
	{
		clientFactory.getRecordClient().createRecord(new CreateRecordRequest(domain, RecordType.A, "test3", "162.243.219.71"));
	}
	
	public static void updateRecord()
	{
		clientFactory.getRecordClient().updateRecord((UpdateRecordRequest) new UpdateRecordRequest(domain, 153849064, RecordType.A, "test3", "162.243.219.71").setRouted(true));
	}
	
	public static void deleteRecord()
	{
		clientFactory.getRecordClient().deleteRecord(new DeleteRecordRequest(domain, 153849091));
	}
	
	public static void createRoutedRecord()
	{
		clientFactory.getRecordClient().createRoutedRecord(new CreateRecordRequest(domain, RecordType.A, "test3", "162.243.219.71"));
	}
	
}
