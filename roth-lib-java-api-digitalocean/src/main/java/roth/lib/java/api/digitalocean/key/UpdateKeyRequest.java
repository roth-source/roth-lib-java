package roth.lib.java.api.digitalocean.key;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class UpdateKeyRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "public_key")
	protected String publicKey;
	
	public UpdateKeyRequest(String name)
	{
		this.name = name;
	}
	
	public UpdateKeyRequest(String name, String publicKey)
	{
		this.name = name;
		this.publicKey = publicKey;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPublicKey()
	{
		return publicKey;
	}
	
	public UpdateKeyRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public UpdateKeyRequest setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
		return this;
	}
	
}
