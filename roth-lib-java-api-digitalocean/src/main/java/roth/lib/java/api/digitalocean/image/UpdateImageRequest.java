package roth.lib.java.api.digitalocean.image;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
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
