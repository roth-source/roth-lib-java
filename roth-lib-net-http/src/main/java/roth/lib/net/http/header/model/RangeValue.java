package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class RangeValue implements Characters
{
	protected String unit;
	protected long start;
	protected long end;
	
	public RangeValue(String unit, long start, long end)
	{
		this.unit = unit;
		this.start = start;
		this.end = end;
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
	
	@Override
	public String toString()
	{
		if(unit != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(unit);
			builder.append(EQUAL);
			builder.append(start);
			builder.append(DASH);
			builder.append(end);
			return builder.toString();
		}
		return null;
	}
	
}
