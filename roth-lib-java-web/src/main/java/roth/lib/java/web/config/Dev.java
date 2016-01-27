package roth.lib.java.web.config;

import java.io.Serializable;
import roth.lib.java.lang.Map;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Dev implements Serializable
{
	@Property(name = "service")
	protected Map<String, Map<String, Scenario>> serviceMap = new Map<String, Map<String, Scenario>>();
	
	public Dev()
	{
		
	}
	
	public Map<String, Map<String, Scenario>> getServiceMap()
	{
		return serviceMap;
	}
	
	public void setServiceMap(Map<String, Map<String, Scenario>> serviceMap)
	{
		this.serviceMap = serviceMap;
	}
	
}
