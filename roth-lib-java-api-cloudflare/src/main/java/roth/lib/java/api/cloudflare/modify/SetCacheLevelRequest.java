package roth.lib.java.api.cloudflare.modify;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.DomainRequest;
import roth.lib.java.api.cloudflare.type.CacheLevelType;

@Entity
@SuppressWarnings("serial")
public class SetCacheLevelRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public SetCacheLevelRequest(String domain, CacheLevelType cacheLevelType)
	{
		super(domain);
		setCacheLevel(cacheLevelType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public SetCacheLevelRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public SetCacheLevelRequest setCacheLevel(CacheLevelType cacheLevelType)
	{
		this.value = cacheLevelType.get();
		return this;
	}
	
	@Override
	public SetCacheLevelRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetCacheLevelRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetCacheLevelRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetCacheLevelRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
