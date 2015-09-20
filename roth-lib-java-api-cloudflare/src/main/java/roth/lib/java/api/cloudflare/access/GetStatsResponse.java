package roth.lib.java.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.model.Stats;

@Entity
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
