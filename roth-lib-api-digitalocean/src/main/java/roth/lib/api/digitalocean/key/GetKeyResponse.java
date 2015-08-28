package roth.lib.api.digitalocean.key;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Key;

@SuppressWarnings("serial")
public class GetKeyResponse implements Serializable
{
	@Property(name = "ssh_key")
	protected Key sshKey;
	
	public GetKeyResponse()
	{
		
	}
	
	public Key getSshKey()
	{
		return sshKey;
	}
	
	public GetKeyResponse setSshKey(Key sshKey)
	{
		this.sshKey = sshKey;
		return this;
	}
	
}
