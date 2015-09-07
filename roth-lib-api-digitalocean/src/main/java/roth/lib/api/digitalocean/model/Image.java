package roth.lib.api.digitalocean.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Image implements Serializable
{
	@Property(name = "id")
	protected Integer id;
	
	@Property(name = "name")
	protected String name;
	
	@Property(name = "distribution")
	protected String distribution;
	
	@Property(name = "slug")
	protected String slug;
	
	@Property(name = "public")
	protected Boolean _public;
	
	@Property(name = "regions")
	protected LinkedList<String> regions;
	
	@Property(name = "created_at")
	protected Calendar createdAt;
	
	public Image()
	{
		
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDistribution()
	{
		return distribution;
	}
	
	public String getSlug()
	{
		return slug;
	}
	
	public Boolean getPublic()
	{
		return _public;
	}
	
	public LinkedList<String> getRegions()
	{
		return regions;
	}
	
	public Calendar getCreatedAt()
	{
		return createdAt;
	}
	
	public Image setId(Integer id)
	{
		this.id = id;
		return this;
	}
	
	public Image setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public Image setDistribution(String distribution)
	{
		this.distribution = distribution;
		return this;
	}
	
	public Image setSlug(String slug)
	{
		this.slug = slug;
		return this;
	}
	
	public Image setPublic(Boolean _public)
	{
		this._public = _public;
		return this;
	}
	
	public Image setRegions(LinkedList<String> regions)
	{
		this.regions = regions;
		return this;
	}
	
	public Image setCreatedAt(Calendar createdAt)
	{
		this.createdAt = createdAt;
		return this;
	}
	
}
