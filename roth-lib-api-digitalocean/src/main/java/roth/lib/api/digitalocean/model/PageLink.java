package roth.lib.api.digitalocean.model;

import java.io.Serializable;

import roth.lib.annotation.Property;

@SuppressWarnings("serial")
public class PageLink implements Serializable
{
	@Property(name = "last")
	protected String last;
	
	@Property(name = "next")
	protected String next;
	
	public PageLink()
	{
		
	}
	
	public String getLast()
	{
		return last;
	}
	
	public String getNext()
	{
		return next;
	}
	
	public PageLink setLast(String last)
	{
		this.last = last;
		return this;
	}
	
	public PageLink setNext(String next)
	{
		this.next = next;
		return this;
	}
	
}