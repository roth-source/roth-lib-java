package roth.lib.java.web.config;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Layout implements Serializable
{
	@Property(name = "renderer")
	protected String renderer;
	
	@Property(name = "init")
	protected String init;
	
	public Layout()
	{
		
	}

	public String getRenderer()
	{
		return renderer;
	}

	public String getInit()
	{
		return init;
	}

	public void setRenderer(String renderer)
	{
		this.renderer = renderer;
	}

	public void setInit(String init)
	{
		this.init = init;
	}
	
}
