package roth.lib.java.web.config;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Page implements Serializable
{
	@Property(name = "renderer")
	protected String renderer;
	
	@Property(name = "init")
	protected String init;
	
	@Property(name = "translated")
	protected Boolean translated;
	
	@Property(name = "text")
	protected String text;
	
	@Property(name = "layout")
	protected String layout;
	
	@Property(name = "service")
	protected String service;
	
	@Property(name = "errorAuthRedirector")
	protected String errorAuthRedirector;
	
	@Property(name = "params")
	protected LinkedList<LinkedHashMap<String, String>> params;
	
	@Property(name = "changeParams")
	protected LinkedList<String> changeParams;
	
	@Property(name = "feedbacker")
	protected String feedbacker;
	
	public Page()
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

	public Boolean getTranslated()
	{
		return translated;
	}

	public String getText()
	{
		return text;
	}

	public String getLayout()
	{
		return layout;
	}

	public String getService()
	{
		return service;
	}

	public String getErrorAuthRedirector()
	{
		return errorAuthRedirector;
	}

	public LinkedList<LinkedHashMap<String, String>> getParams()
	{
		return params;
	}

	public LinkedList<String> getChangeParams()
	{
		return changeParams;
	}

	public String getFeedbacker()
	{
		return feedbacker;
	}

	public void setRenderer(String renderer)
	{
		this.renderer = renderer;
	}

	public void setInit(String init)
	{
		this.init = init;
	}

	public void setTranslated(Boolean translated)
	{
		this.translated = translated;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public void setLayout(String layout)
	{
		this.layout = layout;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public void setErrorAuthRedirector(String errorAuthRedirector)
	{
		this.errorAuthRedirector = errorAuthRedirector;
	}

	public void setParams(LinkedList<LinkedHashMap<String, String>> params)
	{
		this.params = params;
	}

	public void setChangeParams(LinkedList<String> changeParams)
	{
		this.changeParams = changeParams;
	}

	public void setFeedbacker(String feedbacker)
	{
		this.feedbacker = feedbacker;
	}
	
}
