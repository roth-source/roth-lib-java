package roth.lib.api.digitalocean.key;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Key;

@SuppressWarnings("serial")
public class CreateKeyResponse implements Serializable
{
	@Property(name = "ssh_key")
	protected Key sshKey;
	
	public CreateKeyResponse()
	{
		
	}
	
	public Key getSshKey()
	{
		return sshKey;
	}
	
	public CreateKeyResponse setSshKey(Key sshKey)
	{
		this.sshKey = sshKey;
		return this;
	}
	
}
