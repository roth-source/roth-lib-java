package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ContentLocation extends Header<ContentLocation> implements Characters
{
	
	public ContentLocation()
	{
		
	}
	
	public ContentLocation(String value)
	{
		this.value = value;
	}
	
	@Override
	public ContentLocation deserialize(String value)
	{
		return new ContentLocation(value);
	}
	
	@Override
	public ContentLocation parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
