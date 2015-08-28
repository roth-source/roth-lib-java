package roth.lib.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.Records;

@SuppressWarnings("serial")
public class GetRecordsResponse implements Serializable
{
	@Property(name = "recs")
	protected Records recs;
	
	public GetRecordsResponse()
	{
		
	}
	
	public Records getRecs()
	{
		return recs;
	}
	
}
