package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.data.model.Droplet;
import roth.lib.api.digitalocean.data.model.Links;

@SuppressWarnings("serial")
public class DropletResponse implements Serializable
{
	@Property(name = "droplet")
	protected Droplet droplet;
	
	@Property(name = "links")
	protected Links links;
	
	public DropletResponse()
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

	public DropletResponse setDroplet(Droplet droplet)
	{
		this.droplet = droplet;
		return this;
	}

	public DropletResponse setLinks(Links links)
	{
		this.links = links;
		return this;
	}
	
}
