package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Key;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class KeysResponse implements Serializable
{
	@Property(name = "ssh_keys")
	protected LinkedList<Key> keys;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public KeysResponse()
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
	
	public KeysResponse setKeys(LinkedList<Key> keys)
	{
		this.keys = keys;
		return this;
	}
	
	public KeysResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
