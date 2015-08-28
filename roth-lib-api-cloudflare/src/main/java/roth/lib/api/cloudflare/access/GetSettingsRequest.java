package roth.lib.api.cloudflare.access;

import roth.lib.api.cloudflare.DomainRequest;

@SuppressWarnings("serial")
public class GetSettingsRequest extends DomainRequest
{
	
	public GetSettingsRequest(String domain)
	{
		super(domain);
	}
	
	@Override
	public GetSettingsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public GetSettingsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public GetSettingsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public GetSettingsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
