package roth.lib.java.api.cloudflare.model;

import java.io.Serializable;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class TrafficBreakdown implements Serializable
{
	@Property(name = "pageviews")
	protected Traffic pageviews;
	
	@Property(name = "uniques")
	protected Traffic uniques;
	
	public TrafficBreakdown()
	{
		
	}
	
	public Traffic getPageviews()
	{
		return pageviews;
	}
	
	public Traffic getUniques()
	{
		return uniques;
	}
	
	public TrafficBreakdown setPageviews(Traffic pageviews)
	{
		this.pageviews = pageviews;
		return this;
	}
	
	public TrafficBreakdown setUniques(Traffic uniques)
	{
		this.uniques = uniques;
		return this;
	}
	
}
