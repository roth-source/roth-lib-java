package roth.lib.service.endpoint;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
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
