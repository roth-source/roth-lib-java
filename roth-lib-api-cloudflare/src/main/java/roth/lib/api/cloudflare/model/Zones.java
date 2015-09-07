package roth.lib.api.cloudflare.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Zones implements Serializable
{
	@Property(name = "has_more")
	protected Boolean hasMore;
	
	@Property(name = "count")
	protected Integer count;
	
	@Property(name = "objs")
	protected LinkedList<ZoneObj> objs = new LinkedList<ZoneObj>();
	
	public Zones()
	{
		
	}
	
	public Boolean getHasMore()
	{
		return hasMore;
	}
	
	public Integer getCount()
	{
		return count;
	}
	
	public LinkedList<ZoneObj> getObjs()
	{
		return objs;
	}
	
	public Zones setHasMore(Boolean hasMore)
	{
		this.hasMore = hasMore;
		return this;
	}
	
	public Zones setCount(Integer count)
	{
		this.count = count;
		return this;
	}
	
	public Zones setObjs(LinkedList<ZoneObj> objs)
	{
		this.objs = objs;
		return this;
	}
	
}
