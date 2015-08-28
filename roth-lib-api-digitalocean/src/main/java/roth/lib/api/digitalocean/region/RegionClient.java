package roth.lib.api.digitalocean.region;

import roth.lib.api.digitalocean.DigitalOceanClient;

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
	
	public GetRegionsResponse getRegions()
	{
		return get(url(REGIONS), GetRegionsResponse.class);
	}
	
}
