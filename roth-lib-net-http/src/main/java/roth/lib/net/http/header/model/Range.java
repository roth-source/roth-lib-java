package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Range extends Header<Range> implements Characters
{
	protected LinkedList<RangeValue> rangeValues = new LinkedList<RangeValue>();
	
	public Range()
	{
		
	}
	
	public Range(String value)
	{
		this.value = value;
	}
	
	public Range addRangeValue(RangeValue acceptValue)
	{
		rangeValues.add(acceptValue);
		return this;
	}
	
	public LinkedList<RangeValue> getRangeValues()
	{
		return rangeValues;
	}
	
	@Override
	public Range deserialize(String value)
	{
		return new Range(value);
	}
	
	@Override
	public Range parse()
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
		else if(!rangeValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(RangeValue rangeValue : rangeValues)
			{
				String rangeValueString = rangeValue.toString();
				if(rangeValueString != null)
				{
					builder.append(seperator);
					builder.append(rangeValueString);
					if(BLANK.equals(seperator))
					{
						seperator += COMMA;
						seperator += SPACE;
					}
				}
			}
			return builder.toString();
		}
		return null;
	}
	
}
