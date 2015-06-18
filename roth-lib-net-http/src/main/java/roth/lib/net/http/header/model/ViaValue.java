package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ViaValue implements Characters
{
	protected String protocalName;
	protected String protocalVersion;
	protected String host;
	
	public ViaValue(String protocalName, String protocalVersion, String host)
	{
		this.protocalName = protocalName;
		this.protocalVersion = protocalVersion;
		this.host = host;
	}
	
	public String getProtocalName()
	{
		return protocalName;
	}
	
	public String getProtocalVersion()
	{
		return protocalVersion;
	}
	
	public String getHost()
	{
		return host;
	}
	
	@Override
	public String toString()
	{
		if(protocalVersion != null && host != null)
		{
			StringBuilder builder = new StringBuilder();
			if(protocalName != null)
			{
				builder.append(protocalName);
				builder.append(SLASH);
			}
			builder.append(protocalVersion);
			builder.append(SPACE);
			builder.append(host);
			return builder.toString();
		}
		return null;
	}
	
}
