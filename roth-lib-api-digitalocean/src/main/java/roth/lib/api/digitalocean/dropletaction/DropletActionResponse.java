package roth.lib.api.digitalocean.dropletaction;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Action;

@SuppressWarnings("serial")
public class DropletActionResponse implements Serializable
{
	@Property(name = "action")
	protected Action action;
	
	public DropletActionResponse()
	{
		
	}
	
	public Action getAction()
	{
		return action;
	}
	
	public DropletActionResponse setAction(Action action)
	{
		this.action = action;
		return this;
	}
	
}
