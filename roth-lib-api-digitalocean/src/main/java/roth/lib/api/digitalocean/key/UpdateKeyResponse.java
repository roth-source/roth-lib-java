package roth.lib.api.digitalocean.key;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Key;

@SuppressWarnings("serial")
public class UpdateKeyResponse implements Serializable
{
	@Property(name = "ssh_key")
	protected Key sshKey;
	
	public UpdateKeyResponse()
	{
		
	}
	
	public Key getSshKey()
	{
		return sshKey;
	}
	
	public UpdateKeyResponse setSshKey(Key sshKey)
	{
		this.sshKey = sshKey;
		return this;
	}
	
}
