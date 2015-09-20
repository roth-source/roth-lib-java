package roth.lib.java.api.digitalocean.action;

import roth.lib.java.api.digitalocean.DigitalOceanClient;

public class ActionClient extends DigitalOceanClient
{
	
	public ActionClient(String token)
	{
		this(token, false);
	}
	
	public ActionClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public GetActionsResponse getActions(int page)
	{
		return get(url(ACTIONS, page), GetActionsResponse.class);
	}
	
	public GetActionResponse getAction(int id)
	{
		return get(url(ACTIONS + id), GetActionResponse.class);
	}
	
}
