package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.RecordType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class UpdateRecordRequest extends RecordRequest
{
	@Property(name = "id")
	protected Integer id;
	
	@Property(name = "service_mode")
	protected Integer serviceMode;
	
	public UpdateRecordRequest(String domain, int id, RecordType recordType, String name, String content)
	{
		super(domain);
		setId(id);
		setType(recordType);
		setName(name);
		setContent(content);
	}
	
	public UpdateRecordRequest(CreateRecordRequest createRecordRequest, Integer id)
	{
		super(createRecordRequest);
		setId(id);
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public Integer getServiceMode()
	{
		return serviceMode;
	}
	
	public UpdateRecordRequest setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	public UpdateRecordRequest setServiceMode(Integer serviceMode)
	{
		this.serviceMode = serviceMode;
		return this;
	}
	
	public UpdateRecordRequest setRouted(boolean routed)
	{
		this.serviceMode = routed ? 1 : 0;
		return this;
	}
	
	@Override
	public UpdateRecordRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public UpdateRecordRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public UpdateRecordRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
}
