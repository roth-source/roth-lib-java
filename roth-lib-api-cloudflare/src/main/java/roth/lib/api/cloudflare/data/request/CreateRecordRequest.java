package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.RecordType;

@SuppressWarnings("serial")
public class CreateRecordRequest extends RecordRequest
{
	
	public CreateRecordRequest(String domain, RecordType recordType, String name, String content)
	{
		super(domain);
		setType(recordType);
		setName(name);
		setContent(content);
	}
	
}
