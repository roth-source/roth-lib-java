package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.request.ImageActionRequest;
import roth.lib.api.digitalocean.data.response.ActionResponse;
import roth.lib.api.digitalocean.data.type.ImageActionType;
import roth.lib.api.digitalocean.data.type.RegionType;

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
	
	protected ActionResponse createImageAction(int id, ImageActionType imageActionType)
	{
		return createImageAction(id, imageActionType.getImageActionRequest());
	}
	
	protected ActionResponse createImageAction(int id, ImageActionRequest imageActionRequest)
	{
		return post(url(IMAGES + id + ACTIONS), imageActionRequest, ActionResponse.class);
	}
	
	public ActionResponse transferImage(int id, RegionType regionType)
	{
		return createImageAction(id, ImageActionType.TRANSFER.getImageActionRequest().setRegionType(regionType));
	}
	
	public ActionResponse getImageAction(int id, int actionId)
	{
		return get(url(IMAGES + id + ACTIONS + actionId), ActionResponse.class);
	}
	
}
