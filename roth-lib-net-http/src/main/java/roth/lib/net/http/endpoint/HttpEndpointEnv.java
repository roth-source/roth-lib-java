package roth.lib.net.http.endpoint;

import roth.lib.annotation.Property;

public class HttpEndpointEnv
{
	@Property(name = "env")
	protected String env;
	
	public HttpEndpointEnv()
	{
		
	}
	
	public String getEnv()
	{
		return env;
	}
	
	public HttpEndpointEnv setEnv(String env)
	{
		this.env = env;
		return this;
	}
	
}
