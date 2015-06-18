package roth.lib.api.digitalocean.data.request;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ImageRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	public ImageRequest(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ImageRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
}
