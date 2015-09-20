package roth.lib.java.api.digitalocean.size;

import roth.lib.java.api.digitalocean.DigitalOceanClient;

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
	
	public GetSizesResponse getSizes()
	{
		return get(url(SIZES), GetSizesResponse.class);
	}
	
}
