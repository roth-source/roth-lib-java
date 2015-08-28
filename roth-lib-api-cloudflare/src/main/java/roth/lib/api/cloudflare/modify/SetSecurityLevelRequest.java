package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;
import roth.lib.api.cloudflare.type.SecurityLevelType;

@SuppressWarnings("serial")
public class SetSecurityLevelRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public SetSecurityLevelRequest(String domain, SecurityLevelType securityLevelType)
	{
		super(domain);
		setSecurityLevel(securityLevelType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public SetSecurityLevelRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public SetSecurityLevelRequest setSecurityLevel(SecurityLevelType securityLevelType)
	{
		this.value = securityLevelType.get();
		return this;
	}
	
	@Override
	public SetSecurityLevelRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetSecurityLevelRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetSecurityLevelRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetSecurityLevelRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
