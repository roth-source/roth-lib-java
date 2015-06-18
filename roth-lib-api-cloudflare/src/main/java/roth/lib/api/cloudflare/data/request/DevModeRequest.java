package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DevModeRequest extends DomainRequest
{
	@Property(name = "v")
	private Integer value;
	
	public DevModeRequest(String domain, boolean enabled)
	{
		super(domain);
		setDevMode(enabled);
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public DevModeRequest setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
	public DevModeRequest setDevMode(boolean enabled)
	{
		this.value = enabled ? 1 : 0;
		return this;
	}
	
	@Override
	public DevModeRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public DevModeRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public DevModeRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public DevModeRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
