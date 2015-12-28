package roth.lib.java.web.config;

import java.io.Serializable;
import java.util.LinkedHashMap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Properties;

@Entity
@SuppressWarnings("serial")
public class Page implements Serializable
{
	@Properties(first = true)
	protected LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();
	
	public Page()
	{
		
	}
	
}
