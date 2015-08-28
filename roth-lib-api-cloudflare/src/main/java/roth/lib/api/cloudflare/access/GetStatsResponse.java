package roth.lib.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.Stats;

@SuppressWarnings("serial")
public class GetStatsResponse implements Serializable
{
	@Property(name = "result")
	protected Stats result;
	
	public GetStatsResponse()
	{
		
	}
	
	public Stats getResult()
	{
		return result;
	}
	
}
