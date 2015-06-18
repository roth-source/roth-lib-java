package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.response.RegionsResponse;

public class RegionClient extends DigitalOceanClient
{
	
	public RegionClient(String token)
	{
		this(token, false);
	}
	
	public RegionClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public RegionsResponse getRegions()
	{
		return get(url(REGIONS), RegionsResponse.class);
	}
	
}
