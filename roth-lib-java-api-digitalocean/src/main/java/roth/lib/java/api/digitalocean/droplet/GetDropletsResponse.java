package roth.lib.java.api.digitalocean.droplet;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Droplet;
import roth.lib.java.api.digitalocean.model.Meta;

@Entity
@SuppressWarnings("serial")
public class GetDropletsResponse implements Serializable
{
	@Property(name = "droplets")
	protected LinkedList<Droplet> droplets;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetDropletsResponse()
	{
		
	}
	
	public LinkedList<Droplet> getDroplets()
	{
		return droplets;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public GetDropletsResponse setDroplets(LinkedList<Droplet> droplets)
	{
		this.droplets = droplets;
		return this;
	}
	
	public GetDropletsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
