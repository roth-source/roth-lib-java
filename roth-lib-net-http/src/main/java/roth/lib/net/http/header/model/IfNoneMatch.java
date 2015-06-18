package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class IfNoneMatch extends Header<IfNoneMatch> implements Characters
{
	protected LinkedList<String> ifNoneMatches = new LinkedList<String>();
	
	public IfNoneMatch()
	{
		
	}
	
	public IfNoneMatch(String value)
	{
		this.value = value;
	}
	
	public IfNoneMatch addIfNoneMatch(String ifNoneMatch)
	{
		ifNoneMatches.add(ifNoneMatch);
		return this;
	}
	
	public LinkedList<String> getIfNoneMatches()
	{
		return ifNoneMatches;
	}
	
	@Override
	public IfNoneMatch deserialize(String value)
	{
		return new IfNoneMatch(value);
	}
	
	@Override
	public IfNoneMatch parse()
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
		else if(!ifNoneMatches.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(String ifNoneMatch : ifNoneMatches)
			{
				builder.append(seperator);
				builder.append(ifNoneMatch);
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
