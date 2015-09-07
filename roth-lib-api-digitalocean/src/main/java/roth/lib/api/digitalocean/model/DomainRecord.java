package roth.lib.api.digitalocean.model;

import java.io.Serializable;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class DomainRecord implements Serializable
{
	@Property(name = "id")
	protected Integer id;
	
	@Property(name = "type")
	protected String type;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "data")
	protected String data;
	
	@Property(name = "priority")
	protected Integer priority;
	
	@Property(name = "port")
	protected Integer port;
	
	@Property(name = "weight")
	protected Integer weight;
	
	public DomainRecord()
	{
		
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getData()
	{
		return data;
	}
	
	public Integer getPriority()
	{
		return priority;
	}
	
	public Integer getPort()
	{
		return port;
	}
	
	public Integer getWeight()
	{
		return weight;
	}
	
	public DomainRecord setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	public DomainRecord setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public DomainRecord setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public DomainRecord setData(String data)
	{
		this.data = data;
		return this;
	}
	
	public DomainRecord setPriority(Integer priority)
	{
		this.priority = priority;
		return this;
	}
	
	public DomainRecord setPort(Integer port)
	{
		this.port = port;
		return this;
	}
	
	public DomainRecord setWeight(Integer weight)
	{
		this.weight = weight;
		return this;
	}
	
}
