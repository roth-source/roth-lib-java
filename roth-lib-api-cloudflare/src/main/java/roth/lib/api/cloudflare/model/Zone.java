package roth.lib.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Zone implements Serializable
{
	@Property(name = "obj")
	protected ZoneObj obj;
	
	public Zone()
	{
		
	}
	
	public ZoneObj getObj()
	{
		return obj;
	}
	
	public Zone setObj(ZoneObj obj)
	{
		this.obj = obj;
		return this;
	}
	
}
