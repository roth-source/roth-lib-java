package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;

@SuppressWarnings("serial")
public class SetIpv6Request extends DomainRequest
{
	@Property(name = "v")
	protected Integer value;
	
	public SetIpv6Request(String domain, boolean enabled)
	{
		super(domain);
		setIpv6(enabled);
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public SetIpv6Request setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
	public SetIpv6Request setIpv6(boolean enabled)
	{
		this.value = enabled ? 3 : 0;
		return this;
	}
	
	@Override
	public SetIpv6Request setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetIpv6Request setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetIpv6Request setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetIpv6Request setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
