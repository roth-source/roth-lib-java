package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ContentLanguage extends Header<ContentLanguage> implements Characters
{
	
	public ContentLanguage()
	{
		
	}
	
	public ContentLanguage(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	@Override
	public ContentLanguage deserialize(String value)
	{
		return new ContentLanguage(value);
	}
	
	@Override
	public ContentLanguage parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
