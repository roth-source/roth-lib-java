package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.model.Key;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class KeyResponse implements Serializable
{
	@Property(name = "ssh_key")
	protected Key sshKey;
	
	public KeyResponse()
	{
		
	}
	
	public Key getSshKey()
	{
		return sshKey;
	}
	
	public KeyResponse setSshKey(Key sshKey)
	{
		this.sshKey = sshKey;
		return this;
	}
	
}
