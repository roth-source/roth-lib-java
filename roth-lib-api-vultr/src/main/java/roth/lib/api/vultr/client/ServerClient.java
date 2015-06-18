package roth.lib.api.vultr.client;

import roth.lib.api.vultr.request.SubIdRequest;

public class ServerClient extends VultrClient
{
	
	public ServerClient(String apiKey)
	{
		this(apiKey, false);
	}
	
	public ServerClient(String apiKey, boolean debug)
	{
		super(apiKey, debug);
	}
	
	public void getServerBandwidth(SubIdRequest subIdRequest)
	{
		get(url(SERVER + BANDWIDTH, subIdRequest));
	}
	
}
