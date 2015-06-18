package roth.lib.api.cloudflare.data.request;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class FileRequest extends DomainRequest
{
	@Property(name = "url")
	protected String url;
	
	public FileRequest(String domain, String url)
	{
		super(domain);
		this.url = url;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public FileRequest setUrl(String url)
	{
		this.url = url;
		return this;
	}
	
	@Override
	public FileRequest setAction(String action)
	{
		super.setAction(action);
		return this;
	}
	
	@Override
	public FileRequest setEmail(String email)
	{
		super.setEmail(email);
		return this;
	}
	
	@Override
	public FileRequest setToken(String token)
	{
		super.setToken(token);
		return this;
	}
	
	@Override
	public FileRequest setDomain(String domain)
	{
		super.setDomain(domain);
		return this;
	}
	
}
