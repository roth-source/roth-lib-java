package roth.lib.api.digitalocean.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Meta implements Serializable
{
	@Property(name = "total")
	protected Integer total;
	
	public Meta()
	{
		
	}
	
	public Integer getTotal()
	{
		return total;
	}
	
	public Meta setTotal(Integer total)
	{
		this.total = total;
		return this;
	}
	
}
