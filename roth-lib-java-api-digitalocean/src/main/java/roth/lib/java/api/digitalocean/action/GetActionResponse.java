package roth.lib.java.api.digitalocean.action;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Action;

@Entity
@SuppressWarnings("serial")
public class GetActionResponse implements Serializable
{
	@Property(name = "action")
	protected Action action;
	
	public GetActionResponse()
	{
		
	}
	
	public Action getAction()
	{
		return action;
	}
	
	public GetActionResponse setAction(Action action)
	{
		this.action = action;
		return this;
	}
	
}
