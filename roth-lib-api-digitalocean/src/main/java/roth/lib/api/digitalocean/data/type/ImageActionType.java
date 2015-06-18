package roth.lib.api.digitalocean.data.type;

import roth.lib.api.digitalocean.data.request.ImageActionRequest;

public enum ImageActionType
{
	TRANSFER,
	;
	
	public String getName()
	{
		return name().toLowerCase();
	}
	
	public ImageActionRequest getImageActionRequest()
	{
		return new ImageActionRequest().setType(getName());
	}
	
}
