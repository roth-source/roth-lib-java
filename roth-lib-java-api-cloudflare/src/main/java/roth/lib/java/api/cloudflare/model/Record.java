package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Record implements Serializable
{
	@Property(name = "obj")
	protected RecordObj obj;
	
	public Record()
	{
		
	}
	
	public RecordObj getObj()
	{
		return obj;
	}
	
	public Record setObj(RecordObj obj)
	{
		this.obj = obj;
		return this;
	}
	
}
