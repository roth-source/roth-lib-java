package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.SecurityLevelType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class SecurityLevelRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public SecurityLevelRequest(String domain, SecurityLevelType securityLevelType)
	{
		super(domain);
		setSecurityLevel(securityLevelType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public SecurityLevelRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public SecurityLevelRequest setSecurityLevel(SecurityLevelType securityLevelType)
	{
		this.value = securityLevelType.get();
		return this;
	}
	
	@Override
	public SecurityLevelRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SecurityLevelRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SecurityLevelRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SecurityLevelRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
