package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;

@SuppressWarnings("serial")
public class SetMirage2Request extends DomainRequest
{
	@Property(name = "v")
	protected Integer value;
	
	public SetMirage2Request(String domain, boolean enabled)
	{
		super(domain);
		setMirage2(enabled);
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public SetMirage2Request setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
	public SetMirage2Request setMirage2(boolean enabled)
	{
		this.value = enabled ? 1 : 0;
		return this;
	}
	
	@Override
	public SetMirage2Request setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetMirage2Request setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetMirage2Request setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetMirage2Request setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
