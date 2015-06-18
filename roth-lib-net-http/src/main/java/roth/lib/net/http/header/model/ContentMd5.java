package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ContentMd5 extends Header<ContentMd5> implements Characters
{
	
	public ContentMd5()
	{
		
	}
	
	public ContentMd5(String value)
	{
		this.value = value;
	}
	
	@Override
	public ContentMd5 deserialize(String value)
	{
		return new ContentMd5(value);
	}
	
	@Override
	public ContentMd5 parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
