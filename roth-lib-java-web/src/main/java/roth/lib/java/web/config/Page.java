package roth.lib.java.web.config;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Properties;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Page implements Serializable
{
	@Property(name = "params")
	protected LinkedList<LinkedHashMap<String, String>> params;
	
	@Properties
	protected LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
	
	public Page()
	{
		
	}
	
	public LinkedList<LinkedHashMap<String, String>> getParams()
	{
		return params;
	}
	
	public void setParams(LinkedList<LinkedHashMap<String, String>> params)
	{
		this.params = params;
	}
	
}
