package roth.lib.java.api.digitalocean.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Kernel implements Serializable
{
	@Property(name = "id")
	protected Integer id;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "version")
	protected String version;
	
	public Kernel()
	{
		
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public Kernel setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	public Kernel setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Kernel setVersion(String version)
	{
		this.version = version;
		return this;
	}
	
}
