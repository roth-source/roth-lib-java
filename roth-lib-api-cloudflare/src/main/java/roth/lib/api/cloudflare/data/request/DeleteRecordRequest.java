package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DeleteRecordRequest extends DomainRequest
{
	@Property(name = "id")
	protected Integer id;
	
	public DeleteRecordRequest(String domain, int id)
	{
		super(domain);
		this.id = id;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public DeleteRecordRequest setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	@Override
	public DeleteRecordRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public DeleteRecordRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public DeleteRecordRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public DeleteRecordRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
