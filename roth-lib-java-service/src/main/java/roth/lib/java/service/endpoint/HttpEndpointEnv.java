package roth.lib.java.service.endpoint;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

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
