package roth.lib.api.rackspace.data.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Service implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Property(name = "type")
	protected String type;
	
	@Property(name = "endpoints")
	protected LinkedList<Endpoint> endpoints = new LinkedList<Endpoint>();
	
	public Service()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public LinkedList<Endpoint> getEndpoints()
	{
		return endpoints;
	}
	
	public Service setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Service setType(String type)
	{
		this.type = type;
		return this;
	}
	
	public Service setEndpoints(LinkedList<Endpoint> endpoints)
	{
		this.endpoints = endpoints;
		return this;
	}
	
}
