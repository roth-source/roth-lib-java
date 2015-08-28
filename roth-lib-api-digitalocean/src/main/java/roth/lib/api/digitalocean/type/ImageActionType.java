package roth.lib.api.digitalocean.type;

import roth.lib.api.digitalocean.imageaction.ImageActionRequest;

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
