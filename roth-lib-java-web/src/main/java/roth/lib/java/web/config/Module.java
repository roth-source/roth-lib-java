package roth.lib.java.web.config;

import roth.lib.java.lang.Map;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Module extends Page
{
	@Property(name = "page")
	protected Map<String, Page> pageMap = new Map<String, Page>();
	
	public Module()
	{
		properties.remove("params");
	}

	public Map<String, Page> getPageMap()
	{
		return pageMap;
	}

	public void setPageMap(Map<String, Page> pageMap)
	{
		this.pageMap = pageMap;
	}
	
}
