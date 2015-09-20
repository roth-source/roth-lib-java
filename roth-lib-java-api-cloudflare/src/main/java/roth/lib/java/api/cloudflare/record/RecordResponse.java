package roth.lib.java.api.cloudflare.record;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.cloudflare.model.Record;

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
