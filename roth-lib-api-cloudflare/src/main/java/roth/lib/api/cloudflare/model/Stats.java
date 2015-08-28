package roth.lib.api.cloudflare.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class Stats implements Serializable
{
	@Property(name = "timeZero")
	protected Calendar timeZero;
	
	@Property(name = "timeEnd")
	protected Calendar timeEnd;
	
	@Property(name = "count")
	protected Integer count;
	
	@Property(name = "has_more")
	protected Boolean hasMore;
	
	@Property(name = "objs")
	protected LinkedList<StatObj> objs = new LinkedList<StatObj>();
	
	public Stats()
	{
		
	}
	
	public Calendar getTimeZero()
	{
		return timeZero;
	}
	
	public Calendar getTimeEnd()
	{
		return timeEnd;
	}
	
	public Integer getCount()
	{
		return count;
	}
	
	public Boolean getHasMore()
	{
		return hasMore;
	}
	
	public LinkedList<StatObj> getObjs()
	{
		return objs;
	}
	
	public Stats setTimeZero(Calendar timeZero)
	{
		this.timeZero = timeZero;
		return this;
	}
	
	public Stats setTimeEnd(Calendar timeEnd)
	{
		this.timeEnd = timeEnd;
		return this;
	}
	
	public Stats setCount(Integer count)
	{
		this.count = count;
		return this;
	}
	
	public Stats setHasMore(Boolean hasMore)
	{
		this.hasMore = hasMore;
		return this;
	}
	
	public Stats setObjs(LinkedList<StatObj> objs)
	{
		this.objs = objs;
		return this;
	}
	
}
