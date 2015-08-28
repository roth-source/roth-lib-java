package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;

@SuppressWarnings("serial")
public class ClearCacheRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public ClearCacheRequest(String domain)
	{
		super(domain);
		this.value = "1";
	}

	public String getValue()
	{
		return value;
	}
	
	public ClearCacheRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public ClearCacheRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public ClearCacheRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public ClearCacheRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public ClearCacheRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}