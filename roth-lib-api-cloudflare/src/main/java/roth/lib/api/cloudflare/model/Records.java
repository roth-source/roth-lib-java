package roth.lib.api.cloudflare.model;

import java.io.Serializable;
import java.util.LinkedList;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class Records implements Serializable
{
	@Property(name = "has_more")
	protected Boolean hasMore;
	
	@Property(name = "count")
	protected Integer count;
	
	@Property(name = "objs")
	protected LinkedList<RecordObj> objs = new LinkedList<RecordObj>();
	
	public Records()
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
	
	public LinkedList<RecordObj> getObjs()
	{
		return objs;
	}
	
	public Records setHasMore(Boolean hasMore)
	{
		this.hasMore = hasMore;
		return this;
	}
	
	public Records setCount(Integer count)
	{
		this.count = count;
		return this;
	}
	
	public Records setObjs(LinkedList<RecordObj> objs)
	{
		this.objs = objs;
		return this;
	}
	
}
