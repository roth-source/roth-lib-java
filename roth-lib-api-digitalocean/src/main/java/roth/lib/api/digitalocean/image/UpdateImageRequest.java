package roth.lib.api.digitalocean.image;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class UpdateImageRequest implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	public UpdateImageRequest(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public UpdateImageRequest setName(String name)
	{
		this.name = name;
		return this;
	}
	
}
