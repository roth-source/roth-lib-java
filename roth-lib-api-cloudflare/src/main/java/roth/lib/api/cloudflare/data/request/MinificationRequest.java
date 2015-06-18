package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.MinificationType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class MinificationRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public MinificationRequest(String domain, MinificationType minificationType)
	{
		super(domain);
		setMinification(minificationType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public MinificationRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public MinificationRequest setMinification(MinificationType minificationType)
	{
		this.value = minificationType.get();
		return this;
	}
	
	@Override
	public MinificationRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public MinificationRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public MinificationRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public MinificationRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
