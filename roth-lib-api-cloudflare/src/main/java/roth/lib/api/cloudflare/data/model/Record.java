package roth.lib.api.cloudflare.data.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

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
