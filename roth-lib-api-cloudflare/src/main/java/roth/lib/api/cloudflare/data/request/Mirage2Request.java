package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Mirage2Request extends DomainRequest
{
	@Property(name = "v")
	protected Integer value;
	
	public Mirage2Request(String domain, boolean enabled)
	{
		super(domain);
		setMirage2(enabled);
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public Mirage2Request setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
	public Mirage2Request setMirage2(boolean enabled)
	{
		this.value = enabled ? 1 : 0;
		return this;
	}
	
	@Override
	public Mirage2Request setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public Mirage2Request setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public Mirage2Request setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public Mirage2Request setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
