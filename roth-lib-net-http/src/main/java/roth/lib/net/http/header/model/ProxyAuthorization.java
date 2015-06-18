package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ProxyAuthorization extends Header<ProxyAuthorization> implements Characters
{
	protected String type;
	protected String credentials;
	
	public ProxyAuthorization()
	{
		
	}
	
	public ProxyAuthorization(String value)
	{
		this.value = value;
	}
	
	public ProxyAuthorization(String type, String credentials)
	{
		this.type = type;
		this.credentials = credentials;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getCredentials()
	{
		return credentials;
	}
	
	@Override
	public ProxyAuthorization deserialize(String value)
	{
		return new ProxyAuthorization(value);
	}
	
	@Override
	public ProxyAuthorization parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(type != null && value != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(type);
			builder.append(SPACE);
			builder.append(credentials);
			return builder.toString();
		}
		return null;
	}
	
}
