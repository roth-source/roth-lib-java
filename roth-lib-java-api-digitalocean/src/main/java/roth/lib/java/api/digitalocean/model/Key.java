package roth.lib.java.api.digitalocean.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Key implements Serializable
{
	@Property(name = "id")
	protected Integer id;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "fingerprint")
	protected String fingerprint;
	
	@Property(name = "public_key")
	protected String publicKey;
	
	public Key()
	{
		
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getFingerprint()
	{
		return fingerprint;
	}
	
	public String getPublicKey()
	{
		return publicKey;
	}
	
	public Key setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	public Key setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Key setFingerprint(String fingerprint)
	{
		this.fingerprint = fingerprint;
		return this;
	}
	
	public Key setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
		return this;
	}
	
}
