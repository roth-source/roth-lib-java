package roth.lib.api.digitalocean.data.request;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class KeyRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "public_key")
	protected String publicKey;
	
	public KeyRequest(String name)
	{
		this.name = name;
	}
	
	public KeyRequest(String name, String publicKey)
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
	
	public KeyRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public KeyRequest setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
		return this;
	}
	
}
