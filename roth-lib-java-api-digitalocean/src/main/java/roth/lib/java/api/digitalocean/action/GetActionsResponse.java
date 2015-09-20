package roth.lib.java.api.digitalocean.action;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.digitalocean.model.Action;
import roth.lib.java.api.digitalocean.model.Meta;

@Entity
@SuppressWarnings("serial")
public class GetActionsResponse implements Serializable
{
	@Property(name = "actions")
	protected LinkedList<Action> actions;
	
	@Property(name = "meta")
	protected Meta meta;
	
	public GetActionsResponse()
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
	
	public GetActionsResponse setActions(LinkedList<Action> actions)
	{
		this.actions = actions;
		return this;
	}
	
	public GetActionsResponse setMeta(Meta meta)
	{
		this.meta = meta;
		return this;
	}
	
}
