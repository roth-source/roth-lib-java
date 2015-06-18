package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Droplet;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class DropletsResponse implements Serializable
{
	@Property(name = "droplets")
	protected LinkedList<Droplet> droplets;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public DropletsResponse()
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
	
	public DropletsResponse setDroplets(LinkedList<Droplet> droplets)
	{
		this.droplets = droplets;
		return this;
	}
	
	public DropletsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
