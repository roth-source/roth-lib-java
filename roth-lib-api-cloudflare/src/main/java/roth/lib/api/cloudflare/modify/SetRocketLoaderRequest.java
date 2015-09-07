package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;
import roth.lib.api.cloudflare.type.RocketLoaderType;

@Entity
@SuppressWarnings("serial")
public class SetRocketLoaderRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public SetRocketLoaderRequest(String domain, RocketLoaderType rocketLoaderType)
	{
		super(domain);
		setRocketLoader(rocketLoaderType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public SetRocketLoaderRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public SetRocketLoaderRequest setRocketLoader(RocketLoaderType rocketLoaderType)
	{
		this.value = rocketLoaderType.get();
		return this;
	}
	
	@Override
	public SetRocketLoaderRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SetRocketLoaderRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SetRocketLoaderRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SetRocketLoaderRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
