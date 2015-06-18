package roth.lib.api.cloudflare.data.request;


@SuppressWarnings("serial")
public class SettingsRequest extends DomainRequest
{
	
	public SettingsRequest(String domain)
	{
		super(domain);
	}
	
	@Override
	public SettingsRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public SettingsRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public SettingsRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public SettingsRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
