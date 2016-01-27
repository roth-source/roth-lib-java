package roth.lib.java.web.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;

@Entity
@SuppressWarnings("serial")
public class Config implements Serializable
{
	@Property(name = "langs")
	protected Set<String> langs = new Set<String>();
	
	@Property(name = "endpoint")
	protected Map<String, Set<String>> endpointMap = new Map<String, Set<String>>();
	
	@Property(name = "layout")
	protected Map<String, Layout> layoutMap = new Map<String, Layout>();
	
	@Property(name = "module")
	protected Map<String, Module> moduleMap = new Map<String, Module>();
	
	public Config()
	{
		
	}
	
	public Set<String> getLangs()
	{
		return langs;
	}
	
	public Map<String, Set<String>> getEndpointMap()
	{
		return endpointMap;
	}
	
	public Map<String, Layout> getLayoutMap()
	{
		return layoutMap;
	}

	public Map<String, Module> getModuleMap()
	{
		return moduleMap;
	}

	public void setEndpointMap(Map<String, Set<String>> endpointMap)
	{
		this.endpointMap = endpointMap;
	}

	public void setLangs(Set<String> langs)
	{
		this.langs = langs;
	}

	public void setLayoutMap(Map<String, Layout> layoutMap)
	{
		this.layoutMap = layoutMap;
	}

	public void setModuleMap(Map<String, Module> moduleMap)
	{
		this.moduleMap = moduleMap;
	}
	
}
