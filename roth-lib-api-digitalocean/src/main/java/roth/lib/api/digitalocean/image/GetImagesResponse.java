package roth.lib.api.digitalocean.image;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Image;
import roth.lib.api.digitalocean.model.Meta;

@SuppressWarnings("serial")
public class GetImagesResponse implements Serializable
{
	@Property(name = "images")
	protected LinkedList<Image> images;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetImagesResponse()
	{
		
	}
	
	public LinkedList<Image> getImages()
	{
		return images;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public GetImagesResponse setImages(LinkedList<Image> images)
	{
		this.images = images;
		return this;
	}
	
	public GetImagesResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
