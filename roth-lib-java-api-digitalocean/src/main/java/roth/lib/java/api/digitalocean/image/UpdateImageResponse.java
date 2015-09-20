package roth.lib.java.api.digitalocean.image;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Image;

@Entity
@SuppressWarnings("serial")
public class UpdateImageResponse implements Serializable
{
	@Property(name = "image")
	protected Image image;
	
	public UpdateImageResponse()
	{
		
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public UpdateImageResponse setImage(Image image)
	{
		this.image = image;
		return this;
	}
	
}
