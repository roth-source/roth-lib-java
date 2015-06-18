package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Ipv6Request extends DomainRequest
{
	@Property(name = "v")
	protected Integer value;
	
	public Ipv6Request(String domain, boolean enabled)
	{
		super(domain);
		setIpv6(enabled);
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public Ipv6Request setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
	public Ipv6Request setIpv6(boolean enabled)
	{
		this.value = enabled ? 3 : 0;
		return this;
	}
	
	@Override
	public Ipv6Request setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public Ipv6Request setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public Ipv6Request setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public Ipv6Request setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
