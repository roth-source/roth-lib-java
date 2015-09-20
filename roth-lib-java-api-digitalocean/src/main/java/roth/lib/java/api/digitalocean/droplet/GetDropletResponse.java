package roth.lib.java.api.digitalocean.droplet;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Droplet;
import roth.lib.java.api.digitalocean.model.Links;

@Entity
@SuppressWarnings("serial")
public class GetDropletResponse implements Serializable
{
	@Property(name = "droplet")
	protected Droplet droplet;
	
	@Property(name = "links")
	protected Links links;
	
	public GetDropletResponse()
	{
		
	}

	public Droplet getDroplet()
	{
		return droplet;
	}

	public Links getLinks()
	{
		return links;
	}

	public GetDropletResponse setDroplet(Droplet droplet)
	{
		this.droplet = droplet;
		return this;
	}

	public GetDropletResponse setLinks(Links links)
	{
		this.links = links;
		return this;
	}
	
}
