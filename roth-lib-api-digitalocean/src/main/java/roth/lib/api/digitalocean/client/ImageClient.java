package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.ImageRequest;
import roth.lib.api.digitalocean.data.response.ImageResponse;
import roth.lib.api.digitalocean.data.response.ImagesResponse;

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
	
	public ImagesResponse getImages(int page)
	{
		return get(url(IMAGES, page), ImagesResponse.class);
	}
	
	public ImagesResponse getDistributionImages()
	{
		return get(url(IMAGES).setParameter("type", "distribution").setParameter("per_page", "100"), ImagesResponse.class);
	}
	
	public ImageResponse getImage(int id)
	{
		return get(url(IMAGES + id), ImageResponse.class);
	}
	
	public ImageResponse getImage(String slug)
	{
		return get(url(IMAGES + slug), ImageResponse.class);
	}
	
	public ImageResponse updateImage(int id, String name)
	{
		return put(url(IMAGES + id), new ImageRequest(name), ImageResponse.class);
	}
	
	public void deleteImage(int id)
	{
		delete(url(IMAGES + id));
	}
	
}
