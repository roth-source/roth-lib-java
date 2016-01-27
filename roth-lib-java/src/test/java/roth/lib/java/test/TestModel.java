package roth.lib.java.test;

import java.io.Serializable;
import roth.lib.java.lang.Map;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Properties;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class TestModel implements Serializable
{
	@Property(name = "name")
	protected String name;
	
	@Properties
	protected Map<String, Object> map = new Map<String, Object>();
	
	public TestModel()
	{
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public Map<String, Object> getMap()
	{
		return map;
	}
	
	public TestModel setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public TestModel setMap(Map<String, Object> map)
	{
		this.map = map;
		return this;
	}
	
	public TestModel put(String name, Object value)
	{
		this.map.put(name, value);
		return this;
	}
	
}
