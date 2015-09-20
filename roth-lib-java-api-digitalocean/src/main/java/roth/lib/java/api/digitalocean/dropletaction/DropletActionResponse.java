package roth.lib.java.api.digitalocean.dropletaction;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Action;

@Entity
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
