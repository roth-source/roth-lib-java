package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ETag extends Header<ETag> implements Characters
{
	
	public ETag()
	{
		
	}
	
	public ETag(String value)
	{
		this.value = value;
	}
	
	@Override
	public ETag deserialize(String value)
	{
		return new ETag(value);
	}
	
	@Override
	public ETag parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
