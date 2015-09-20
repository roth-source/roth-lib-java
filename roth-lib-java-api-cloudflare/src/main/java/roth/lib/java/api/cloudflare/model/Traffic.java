package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Traffic implements Serializable
{
	@Property(name = "regular")
	protected Integer regular;
	
	@Property(name = "threat")
	protected Integer threat;
	
	@Property(name = "crawler")
	protected Integer crawler;
	
	public Traffic()
	{
		
	}
	
	public Integer getRegular()
	{
		return regular;
	}
	
	public Integer getThreat()
	{
		return threat;
	}
	
	public Integer getCrawler()
	{
		return crawler;
	}
	
	public Traffic setRegular(Integer regular)
	{
		this.regular = regular;
		return this;
	}
	
	public Traffic setThreat(Integer threat)
	{
		this.threat = threat;
		return this;
	}
	
	public Traffic setCrawler(Integer crawler)
	{
		this.crawler = crawler;
		return this;
	}
	
}
