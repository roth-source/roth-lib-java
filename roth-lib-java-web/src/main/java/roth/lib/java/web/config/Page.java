package roth.lib.java.web.config;

import java.io.Serializable;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.List;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Properties;

@Entity
@SuppressWarnings("serial")
public class Page implements Serializable
{
	@Properties(first = true)
	protected Map<String, Object> properties = new Map<String, Object>();
	
	public Page()
	{
		List<Map<String, String>> params = new List<Map<String, String>>();
		params.add(new Map<String, String>());
		properties.put("params", params);
	}
	
}
