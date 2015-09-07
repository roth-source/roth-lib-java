package roth.lib.api.digitalocean.action;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.digitalocean.model.Action;

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
