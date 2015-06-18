package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class IfMatch extends Header<IfMatch> implements Characters
{
	protected LinkedList<String> ifMatches = new LinkedList<String>();
	
	public IfMatch()
	{
		
	}
	
	public IfMatch(String value)
	{
		this.value = value;
	}
	
	public IfMatch addIfMatch(String ifMatch)
	{
		ifMatches.add(ifMatch);
		return this;
	}
	
	public LinkedList<String> getIfMatches()
	{
		return ifMatches;
	}
	
	@Override
	public IfMatch deserialize(String value)
	{
		return new IfMatch(value);
	}
	
	@Override
	public IfMatch parse()
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
		else if(!ifMatches.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(String ifMatch : ifMatches)
			{
				builder.append(seperator);
				builder.append(ifMatch);
				if(BLANK.equals(seperator))
				{
					seperator += COMMA;
					seperator += SPACE;
				}
			}
			return builder.toString();
		}
		return null;
	}
	
}
