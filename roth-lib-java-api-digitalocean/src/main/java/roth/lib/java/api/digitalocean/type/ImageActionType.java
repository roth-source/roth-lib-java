package roth.lib.java.api.digitalocean.type;

import roth.lib.java.api.digitalocean.imageaction.ImageActionRequest;

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
