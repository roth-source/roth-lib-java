package roth.lib.api.rackspace.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class AuthCredentials implements Serializable
{
	@Property(name = "RAX-KSKEY:apiKeyCredentials")
	protected ApiKeyCredentials apiKeyCredentials;
	
	@Property(name = "passwordCredentials")
	protected PasswordCredentials passwordCredentials;
	
	public AuthCredentials(ApiKeyCredentials apiKeyCredentials)
	{
		this.apiKeyCredentials = apiKeyCredentials;
	}
	
	public AuthCredentials(PasswordCredentials passwordCredentials)
	{
		this.passwordCredentials = passwordCredentials;
	}
	
	public ApiKeyCredentials getApiKeyCredentials()
	{
		return apiKeyCredentials;
	}
	
	public PasswordCredentials getPasswordCredentials()
	{
		return passwordCredentials;
	}
	
	public AuthCredentials setApiKeyCredentials(ApiKeyCredentials apiKeyCredentials)
	{
		this.apiKeyCredentials = apiKeyCredentials;
		return this;
	}
	
	public AuthCredentials setPasswordCredentials(PasswordCredentials passwordCredentials)
	{
		this.passwordCredentials = passwordCredentials;
		return this;
	}
	
}
