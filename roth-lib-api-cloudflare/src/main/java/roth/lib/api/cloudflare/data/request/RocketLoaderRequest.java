package roth.lib.api.cloudflare.data.request;

import roth.lib.api.cloudflare.data.type.RocketLoaderType;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RocketLoaderRequest extends DomainRequest
{
	@Property(name = "v")
	protected String value;
	
	public RocketLoaderRequest(String domain, RocketLoaderType rocketLoaderType)
	{
		super(domain);
		setRocketLoader(rocketLoaderType);
	}
	
	public String getValue()
	{
		return value;
	}
	
	public RocketLoaderRequest setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	public RocketLoaderRequest setRocketLoader(RocketLoaderType rocketLoaderType)
	{
		this.value = rocketLoaderType.get();
		return this;
	}
	
	@Override
	public RocketLoaderRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public RocketLoaderRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public RocketLoaderRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public RocketLoaderRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
