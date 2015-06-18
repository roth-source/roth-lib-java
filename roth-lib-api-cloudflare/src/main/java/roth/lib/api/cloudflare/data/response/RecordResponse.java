package roth.lib.api.cloudflare.data.response;

import java.io.Serializable;

import roth.lib.api.cloudflare.data.model.Record;
import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class RecordResponse implements Serializable
{
	@Property(name = "rec")
	protected Record rec;
	
	public RecordResponse()
	{
		
	}
	
	public Record getRec()
	{
		return rec;
	}
	
}
