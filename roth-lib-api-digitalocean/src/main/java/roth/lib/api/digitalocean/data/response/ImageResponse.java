package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.model.Image;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ImageResponse implements Serializable
{
	@Property(name = "image")
	protected Image image;
	
	public ImageResponse()
	{
		
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public ImageResponse setImage(Image image)
	{
		this.image = image;
		return this;
	}
	
}
