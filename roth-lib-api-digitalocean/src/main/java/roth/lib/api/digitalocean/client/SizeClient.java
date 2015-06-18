package roth.lib.api.digitalocean.client;

import roth.lib.api.digitalocean.data.response.SizesResponse;

public class SizeClient extends DigitalOceanClient
{
	
	public SizeClient(String token)
	{
		this(token, false);
	}
	
	public SizeClient(String token, boolean debug)
	{
		super(token, debug);
	}
	
	public SizesResponse getSizes()
	{
		return get(url(SIZES), SizesResponse.class);
	}
	
}
