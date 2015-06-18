package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Allow extends Header<Allow> implements Characters
{
	protected LinkedList<String> allows = new LinkedList<String>();
	
	public Allow()
	{
		
	}
	
	public Allow(String value)
	{
		this.value = value;
	}
	
	public Allow addAllow(String allow)
	{
		allows.add(allow);
		return this;
	}
	
	public LinkedList<String> getAllows()
	{
		return allows;
	}
	
	@Override
	public Allow deserialize(String value)
	{
		return new Allow(value);
	}
	
	@Override
	public Allow parse()
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
		else if(!allows.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(String allow : allows)
			{
				builder.append(seperator);
				builder.append(allow);
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
