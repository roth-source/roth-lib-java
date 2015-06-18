package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class IfRange extends Header<IfRange> implements Characters
{
	
	public IfRange()
	{
		
	}
	
	public IfRange(String value)
	{
		this.value = value;
	}
	
	@Override
	public IfRange deserialize(String value)
	{
		
		return new IfRange(value);
	}
	
	@Override
	public IfRange parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
