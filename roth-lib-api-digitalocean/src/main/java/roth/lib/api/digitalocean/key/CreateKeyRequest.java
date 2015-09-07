package roth.lib.api.digitalocean.key;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class CreateKeyRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "public_key")
	protected String publicKey;
	
	public CreateKeyRequest(String name)
	{
		this.name = name;
	}
	
	public CreateKeyRequest(String name, String publicKey)
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
	
	public CreateKeyRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public CreateKeyRequest setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
		return this;
	}
	
}
