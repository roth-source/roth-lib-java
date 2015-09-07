package roth.lib.api.cloudflare.record;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;
import roth.lib.api.cloudflare.model.Record;

@Entity
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
