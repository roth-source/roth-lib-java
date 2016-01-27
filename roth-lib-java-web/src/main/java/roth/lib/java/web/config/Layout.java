package roth.lib.java.web.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Properties;
import roth.lib.java.lang.Map;

@Entity
@SuppressWarnings("serial")
public class Layout implements Serializable
{
	@Properties(first = true)
	protected Map<String, Object> properties = new Map<String, Object>();
	
	public Layout()
	{
		
	}
	
}
