package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ContentRange extends Header<ContentRange> implements Characters
{
	protected String unit;
	protected long start;
	protected long end;
	protected long length;
	
	public ContentRange()
	{
		
	}
	
	public ContentRange(String value)
	{
		this.value = value;
	}
	
	public ContentRange(String unit, long start, long end, long length)
	{
		this.unit = unit;
		this.start = start;
		this.end = end;
		this.length = length;
	}
	
	public String getUnit()
	{
		return unit;
	}
	
	public long getStart()
	{
		return start;
	}
	
	public long getEnd()
	{
		return end;
	}
	
	public long getLength()
	{
		return length;
	}
	
	@Override
	public ContentRange deserialize(String value)
	{
		return new ContentRange(value);
	}
	
	@Override
	public ContentRange parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(unit != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(unit);
			builder.append(EQUAL);
			builder.append(start);
			builder.append(DASH);
			builder.append(end);
			builder.append(SLASH);
			builder.append(length);
			return builder.toString();
		}
		return null;
	}
	
}
