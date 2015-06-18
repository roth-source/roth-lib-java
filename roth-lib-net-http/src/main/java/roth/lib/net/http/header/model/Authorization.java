package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Authorization extends Header<Authorization> implements Characters
{
	protected String type;
	protected String credentials;
	
	public Authorization()
	{
		
	}
	
	public Authorization(String value)
	{
		this.value = value;
	}
	
	public Authorization(String type, String credentials)
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
	public Authorization deserialize(String value)
	{
		return new Authorization(value);
	}
	
	@Override
	public Authorization parse()
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
		else if(type != null && credentials != null)
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
