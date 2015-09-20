package roth.lib.java.api.linode.data.response;

import java.io.Serializable;
import java.util.LinkedHashMap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.api.linode.data.model.Method;

@Entity
@SuppressWarnings("serial")
public class ApiSpecResponse implements Serializable
{
	@Property(name = "VERSION")
	protected Double version;
	
	@Property(name = "METHODS")
	protected LinkedHashMap<String, Method> methods;
	
	public ApiSpecResponse()
	{
		
	}
	
	public Double getVersion()
	{
		return version;
	}
	
	public LinkedHashMap<String, Method> getMethods()
	{
		return methods;
	}
	
	public ApiSpecResponse setVersion(Double version)
	{
		this.version = version;
		return this;
	}
	
	public ApiSpecResponse setMethods(LinkedHashMap<String, Method> methods)
	{
		this.methods = methods;
		return this;
	}
	
}
