package roth.lib.api.linode.data.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Method implements Serializable
{
	@Property(name = "DESCRIPTION")
	protected String description;
	
	@Property(name = "PARAMETERS")
	protected LinkedHashMap<String, Parameter> parameters;
	
	@Property(name = "THROWS")
	protected String _throws;
	
	public Method()
	{
		
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public LinkedHashMap<String, Parameter> getParameters()
	{
		return parameters;
	}
	
	public String getThrows()
	{
		return _throws;
	}
	
	public Method setDescription(String description)
	{
		this.description = description;
		return this;
	}
	
	public Method setParameters(LinkedHashMap<String, Parameter> parameters)
	{
		this.parameters = parameters;
		return this;
	}
	
	public Method setThrows(String _throws)
	{
		this._throws = _throws;
		return this;
	}
	
}
