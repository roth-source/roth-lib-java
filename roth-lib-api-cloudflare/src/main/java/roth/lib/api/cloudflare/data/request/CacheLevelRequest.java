package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.CacheLevelType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class CacheLevelRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public CacheLevelRequest(String domain, CacheLevelType cacheLevelType)
	{
		super(domain);
		setCacheLevel(cacheLevelType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public CacheLevelRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public CacheLevelRequest setCacheLevel(CacheLevelType cacheLevelType)
	{
		this.value = cacheLevelType.get();
		return this;
	}
	
	@Override
	public CacheLevelRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public CacheLevelRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public CacheLevelRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public CacheLevelRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
