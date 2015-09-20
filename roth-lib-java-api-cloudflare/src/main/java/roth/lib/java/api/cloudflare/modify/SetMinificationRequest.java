package roth.lib.java.api.cloudflare.modify;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.DomainRequest;
import roth.lib.java.api.cloudflare.type.MinificationType;

@Entity
@SuppressWarnings("serial")
public class SetMinificationRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public SetMinificationRequest(String domain, MinificationType minificationType)
	{
		super(domain);
		setMinification(minificationType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public SetMinificationRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public SetMinificationRequest setMinification(MinificationType minificationType)
	{
		this.value = minificationType.get();
		return this;
	}
	
	@Override
	public SetMinificationRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetMinificationRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetMinificationRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetMinificationRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
