package roth.lib.java.api.digitalocean.imageaction;

import roth.lib.java.api.digitalocean.DigitalOceanClient;
import roth.lib.java.api.digitalocean.type.ImageActionType;
import roth.lib.java.api.digitalocean.type.RegionType;

public class ImageActionClient extends DigitalOceanClient
{
	
	public ImageActionClient(String token)
	{
		this(token, false);
	}
	
	public ImageActionClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	protected ImageActionResponse createImageAction(int id, ImageActionType imageActionType)
	{
		return createImageAction(id, imageActionType.getImageActionRequest());
	}
	
	protected ImageActionResponse createImageAction(int id, ImageActionRequest imageActionRequest)
	{
		return post(url(IMAGES + id + ACTIONS), imageActionRequest, ImageActionResponse.class);
	}
	
	public ImageActionResponse transferImage(int id, RegionType regionType)
	{
		return createImageAction(id, ImageActionType.TRANSFER.getImageActionRequest().setRegionType(regionType));
	}
	
	public ImageActionResponse getImageAction(int id, int actionId)
	{
		return get(url(IMAGES + id + ACTIONS + actionId), ImageActionResponse.class);
	}
	
}
