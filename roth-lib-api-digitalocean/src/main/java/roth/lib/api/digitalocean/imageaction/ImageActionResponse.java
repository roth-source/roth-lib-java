package roth.lib.api.digitalocean.imageaction;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Action;

@Entity
@SuppressWarnings("serial")
public class ImageActionResponse implements Serializable
{
	@Property(name = "action")
	protected Action action;
	
	public ImageActionResponse()
	{
		
	}
	
	public Action getAction()
	{
		return action;
	}
	
	public ImageActionResponse setAction(Action action)
	{
		this.action = action;
		return this;
	}
	
}
