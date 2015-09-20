package roth.lib.java.api.digitalocean.image;

import roth.lib.java.api.digitalocean.DigitalOceanClient;

public class ImageClient extends DigitalOceanClient
{
	
	public ImageClient(String token)
	{
		this(token, false);
	}
	
	public ImageClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public GetImagesResponse getImages(int page)
	{
		return get(url(IMAGES, page), GetImagesResponse.class);
	}
	
	public GetImagesResponse getDistributionImages()
	{
		return get(url(IMAGES).setParameter("type", "distribution").setParameter("per_page", "100"), GetImagesResponse.class);
	}
	
	public GetImageResponse getImage(int id)
	{
		return get(url(IMAGES + id), GetImageResponse.class);
	}
	
	public GetImageResponse getImage(String slug)
	{
		return get(url(IMAGES + slug), GetImageResponse.class);
	}
	
	public UpdateImageResponse updateImage(int id, String name)
	{
		return put(url(IMAGES + id), new UpdateImageRequest(name), UpdateImageResponse.class);
	}
	
	public void deleteImage(int id)
	{
		delete(url(IMAGES + id));
	}
	
}
