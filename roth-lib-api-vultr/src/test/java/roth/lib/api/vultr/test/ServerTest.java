package roth.lib.api.vultr.test;

import roth.lib.api.vultr.request.SubIdRequest;

public class ServerTest extends Test
{
	
	public static void main(String[] args)
	{
		getServerBandwidth();
	}
	
	protected static void getServerBandwidth()
	{
		clientFactory.getServerClient().getServerBandwidth(new SubIdRequest("1649930"));
	}
	
}
