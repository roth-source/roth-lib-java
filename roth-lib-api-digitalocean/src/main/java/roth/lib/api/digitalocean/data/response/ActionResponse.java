package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;

import roth.lib.api.digitalocean.data.model.Action;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ActionResponse implements Serializable
{
	@Property(name = "action")
	protected Action action;
	
	public ActionResponse()
	{
		
	}
	
	public Action getAction()
	{
		return action;
	}
	
	public ActionResponse setAction(Action action)
	{
		this.action = action;
		return this;
	}
	
}
