package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;

import roth.lib.api.cloudflare.data.model.Stats;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class StatsResponse implements Serializable
{
	@Property(name = "result")
	protected Stats result;
	
	public StatsResponse()
	{
		
	}
	
	public Stats getResult()
	{
		return result;
	}
	
}
