package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.response.ActionResponse;
import roth.lib.api.digitalocean.data.response.ActionsResponse;

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
	
	public ActionsResponse getActions(int page)
	{
		return get(url(ACTIONS, page), ActionsResponse.class);
	}
	
	public ActionResponse getAction(int id)
	{
		return get(url(ACTIONS + id), ActionResponse.class);
	}
	
}
