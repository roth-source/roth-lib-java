package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class CacheRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public CacheRequest(String domain)
	{
		super(domain);
		this.value = "1";
	}

	public String getValue()
	{
		return value;
	}
	
	public CacheRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public CacheRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public CacheRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public CacheRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public CacheRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
