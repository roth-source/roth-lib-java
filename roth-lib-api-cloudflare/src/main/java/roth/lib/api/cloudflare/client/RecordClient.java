package roth.lib.api.cloudflare.client;

import roth.lib.api.cloudflare.data.request.CreateRecordRequest;
import roth.lib.api.cloudflare.data.request.DeleteRecordRequest;
import roth.lib.api.cloudflare.data.request.UpdateRecordRequest;
import roth.lib.api.cloudflare.data.response.CloudFlareResponse;
import roth.lib.api.cloudflare.data.response.RecordResponse;
import roth.lib.map.Generic;

public class RecordClient extends CloudFlareClient
{
	protected static String CREATE_RECORD		= "rec_new";
	protected static String UPDATE_RECORD		= "rec_edit";
	protected static String DELETE_RECORD		= "rec_delete";
	
	public RecordClient(String email, String token)
	{
		this(email, token, false);
	}
	
	public RecordClient(String email, String token, boolean debug)
	{
		super(email, token, debug);
	}
	
	public CloudFlareResponse<RecordResponse> createRecord(CreateRecordRequest createRecordRequest)
	{
		return post(url(), createRecordRequest.setAction(CREATE_RECORD), new Generic<CloudFlareResponse<RecordResponse>>(){});
	}
	
	public CloudFlareResponse<RecordResponse> updateRecord(UpdateRecordRequest updateRecordRequest)
	{
		return post(url(), updateRecordRequest.setAction(UPDATE_RECORD), new Generic<CloudFlareResponse<RecordResponse>>(){});
	}
	
	public CloudFlareResponse<Void> deleteRecord(DeleteRecordRequest deleteRecordRequest)
	{
		return post(url(), deleteRecordRequest.setAction(DELETE_RECORD), new Generic<CloudFlareResponse<Void>>(){});
	}
	
	public CloudFlareResponse<RecordResponse> createRoutedRecord(CreateRecordRequest createRecordRequest)
	{
		CloudFlareResponse<RecordResponse> serviceRequest = createRecord(createRecordRequest);
		String id = serviceRequest.getResponse().getRec().getObj().getRecId();
		return updateRecord(new UpdateRecordRequest(createRecordRequest, Integer.valueOf(id)));
	}
	
}
