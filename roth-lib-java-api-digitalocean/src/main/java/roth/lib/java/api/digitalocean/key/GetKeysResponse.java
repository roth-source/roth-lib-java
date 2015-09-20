package roth.lib.java.api.digitalocean.key;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Key;
import roth.lib.java.api.digitalocean.model.Meta;

@Entity
@SuppressWarnings("serial")
public class GetKeysResponse implements Serializable
{
	@Property(name = "ssh_keys")
	protected LinkedList<Key> keys;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetKeysResponse()
	{
		
	}
	
	public LinkedList<Key> getKeys()
	{
		return keys;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public GetKeysResponse setKeys(LinkedList<Key> keys)
	{
		this.keys = keys;
		return this;
	}
	
	public GetKeysResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
