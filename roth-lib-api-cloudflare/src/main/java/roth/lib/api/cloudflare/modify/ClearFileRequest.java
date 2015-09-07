package roth.lib.api.cloudflare.modify;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.DomainRequest;

@Entity
@SuppressWarnings("serial")
public class ClearFileRequest extends DomainRequest
{
	@Property(name = "url")
	protected String url;
	
	public ClearFileRequest(String domain, String url)
	{
		super(domain);
		this.url = url;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public ClearFileRequest setUrl(String url)
	{
		this.url = url;
		return this;
	}
	
	@Override
	public ClearFileRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public ClearFileRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public ClearFileRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public ClearFileRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
