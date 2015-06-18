package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Image;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ImagesResponse implements Serializable
{
	@Property(name = "images")
	protected LinkedList<Image> images;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public ImagesResponse()
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
	
	public ImagesResponse setImages(LinkedList<Image> images)
	{
		this.images = images;
		return this;
	}
	
	public ImagesResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
