package roth.lib.java.api.digitalocean.key;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Key;

@Entity
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
