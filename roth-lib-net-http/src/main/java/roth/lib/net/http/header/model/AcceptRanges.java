package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class AcceptRanges extends Header<AcceptRanges> implements Characters
{
	
	public AcceptRanges()
	{
		
	}
	
	public AcceptRanges(String value)
	{
		this.value = value;
	}
	
	@Override
	public AcceptRanges deserialize(String value)
	{
		return new AcceptRanges(value);
	}
	
	@Override
	public AcceptRanges parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
	
}
