package roth.lib.api.cloudflare.access;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;

@SuppressWarnings("serial")
public class GetRecordsRequest extends DomainRequest
{
	@Property(name = "o")
	protected Integer offset;
	
	public GetRecordsRequest(String domain)
	{
		super(domain);
	}
	
	public Integer getOffset()
	{
		return offset;
	}
	
	public GetRecordsRequest setOffset(Integer offset)
	{
		this.offset = offset;
		return this;
	}
	
	@Override
	public GetRecordsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public GetRecordsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public GetRecordsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public GetRecordsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
