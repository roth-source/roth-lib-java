package roth.lib.java._static.config;

import java.io.Serializable;
import java.util.LinkedHashMap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Dev implements Serializable
{
	@Property(name = "service")
	protected LinkedHashMap<String, LinkedHashMap<String, Scenario>> serviceMap = new LinkedHashMap<String, LinkedHashMap<String, Scenario>>();
	
	public Dev()
	{
		
	}
	
	public LinkedHashMap<String, LinkedHashMap<String, Scenario>> getServiceMap()
	{
		return serviceMap;
	}
	
	public void setServiceMap(LinkedHashMap<String, LinkedHashMap<String, Scenario>> serviceMap)
	{
		this.serviceMap = serviceMap;
	}
	
}
