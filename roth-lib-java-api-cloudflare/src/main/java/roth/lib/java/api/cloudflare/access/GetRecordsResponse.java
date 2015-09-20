package roth.lib.java.api.cloudflare.access;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.model.Records;

@Entity
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
