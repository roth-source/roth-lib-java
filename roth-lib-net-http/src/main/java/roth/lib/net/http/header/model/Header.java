package roth.lib.net.http.header.model;

import roth.lib.Characters;
import roth.lib.map.Deserializer;

public abstract class Header<T> extends Deserializer<T> implements Characters
{
	protected String value;
	
	public Header()
	{
		
	}
	
	public Header(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public abstract T parse();
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
