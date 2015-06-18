package roth.lib.api.digitalocean.data.response;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.api.digitalocean.data.model.Action;
import roth.lib.api.digitalocean.data.model.Meta;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class ActionsResponse implements Serializable
{
	@Property(name = "actions")
	protected LinkedList<Action> actions;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public ActionsResponse()
	{
		
	}
	
	public LinkedList<Action> getActions()
	{
		return actions;
	}
	
	public Meta getMeta()
	{
		return meta;
	}
	
	public ActionsResponse setActions(LinkedList<Action> actions)
	{
		this.actions = actions;
		return this;
	}
	
	public ActionsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
