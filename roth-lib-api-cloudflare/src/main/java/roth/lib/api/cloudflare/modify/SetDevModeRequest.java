package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;

@SuppressWarnings("serial")
public class SetDevModeRequest extends DomainRequest
{
	@Property(name = "v")
	private Integer value;
	
	public SetDevModeRequest(String domain, boolean enabled)
	{
		super(domain);
		setDevMode(enabled);
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public SetDevModeRequest setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
	public SetDevModeRequest setDevMode(boolean enabled)
	{
		this.value = enabled ? 1 : 0;
		return this;
	}
	
	@Override
	public SetDevModeRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetDevModeRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetDevModeRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetDevModeRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
