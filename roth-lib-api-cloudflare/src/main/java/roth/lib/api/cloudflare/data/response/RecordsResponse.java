package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;

import roth.lib.api.cloudflare.data.model.Records;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RecordsResponse implements Serializable
{
	@Property(name = "recs")
	protected Records recs;
	
	public RecordsResponse()
	{
		
	}
	
	public Records getRecs()
	{
		return recs;
	}
	
}
