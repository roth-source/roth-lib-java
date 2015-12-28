package roth.lib.java.web.config;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Module extends Page
{
	@Property(name = "page")
	protected LinkedHashMap<String, Page> pageMap = new LinkedHashMap<String, Page>();
	
	public Module()
	{
		LinkedList<LinkedHashMap<String, String>> params = new LinkedList<LinkedHashMap<String, String>>();
		params.add(new LinkedHashMap<String, String>());
		properties.put("params", params);
	}

	public LinkedHashMap<String, Page> getPageMap()
	{
		return pageMap;
	}

	public void setPageMap(LinkedHashMap<String, Page> pageMap)
	{
		this.pageMap = pageMap;
	}
	
}
