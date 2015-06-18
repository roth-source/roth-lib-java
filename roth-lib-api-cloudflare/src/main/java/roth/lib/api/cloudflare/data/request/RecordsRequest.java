package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RecordsRequest extends DomainRequest
{
	@Property(name = "o")
	protected Integer offset;
	
	public RecordsRequest(String domain)
	{
		super(domain);
	}
	
	public Integer getOffset()
	{
		return offset;
	}
	
	public RecordsRequest setOffset(Integer offset)
	{
		this.offset = offset;
		return this;
	}
	
	@Override
	public RecordsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public RecordsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public RecordsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public RecordsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
