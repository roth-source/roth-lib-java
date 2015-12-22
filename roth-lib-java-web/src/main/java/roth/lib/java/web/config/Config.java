package roth.lib.java.web.config;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Config implements Serializable
{
	@Property(name = "langs")
	protected LinkedHashSet<String> langs = new LinkedHashSet<String>();
	
	@Property(name = "endpoint")
	protected LinkedHashMap<String, LinkedHashSet<String>> endpointMap = new LinkedHashMap<String, LinkedHashSet<String>>();
	
	@Property(name = "layout")
	protected LinkedHashMap<String, Layout> layoutMap = new LinkedHashMap<String, Layout>();
	
	@Property(name = "module")
	protected LinkedHashMap<String, Module> moduleMap = new LinkedHashMap<String, Module>();
	
	public Config()
	{
		
	}
	
	public LinkedHashSet<String> getLangs()
	{
		return langs;
	}
	
	public LinkedHashMap<String, LinkedHashSet<String>> getEndpointMap()
	{
		return endpointMap;
	}
	
	public LinkedHashMap<String, Layout> getLayoutMap()
	{
		return layoutMap;
	}

	public LinkedHashMap<String, Module> getModuleMap()
	{
		return moduleMap;
	}

	public void setEndpointMap(LinkedHashMap<String, LinkedHashSet<String>> endpointMap)
	{
		this.endpointMap = endpointMap;
	}

	public void setLangs(LinkedHashSet<String> langs)
	{
		this.langs = langs;
	}

	public void setLayoutMap(LinkedHashMap<String, Layout> layoutMap)
	{
		this.layoutMap = layoutMap;
	}

	public void setModuleMap(LinkedHashMap<String, Module> moduleMap)
	{
		this.moduleMap = moduleMap;
	}
	
}
