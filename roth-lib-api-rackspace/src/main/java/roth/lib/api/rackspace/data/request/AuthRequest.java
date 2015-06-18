package roth.lib.api.rackspace.data.request;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.rackspace.data.model.ApiKeyCredentials;
import roth.lib.api.rackspace.data.model.AuthCredentials;

@SuppressWarnings("serial")
public class AuthRequest implements Serializable
{
	@Property(name = "auth")
	protected AuthCredentials authCredentials;
	
	public AuthRequest(AuthCredentials authCredentials)
	{
		this.authCredentials = authCredentials;
	}
	
	public AuthRequest(String username, String apiKey)
	{
		this.authCredentials = new AuthCredentials(new ApiKeyCredentials(username, apiKey));
	}
	
	public AuthCredentials getAuthCredentials()
	{
		return authCredentials;
	}
	
	public AuthRequest setAuthCredentials(AuthCredentials authCredentials)
	{
		this.authCredentials = authCredentials;
		return this;
	}
	
}
