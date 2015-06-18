package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Accept extends Header<Accept> implements Characters
{
	protected LinkedList<AcceptValue> acceptValues = new LinkedList<AcceptValue>();
	
	public Accept()
	{
		
	}
	
	public Accept(String value)
	{
		this.value = value;
	}
	
	public Accept addAcceptValue(AcceptValue acceptValue)
	{
		acceptValues.add(acceptValue);
		return this;
	}
	
	public LinkedList<AcceptValue> getAcceptValues()
	{
		return acceptValues;
	}
	
	@Override
	public Accept deserialize(String value)
	{
		return new Accept(value);
	}
	
	@Override
	public Accept parse()
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
		else if(!acceptValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(AcceptValue acceptValue : acceptValues)
			{
				String acceptValueString = acceptValue.toString();
				if(acceptValueString != null)
				{
					builder.append(seperator);
					builder.append(acceptValueString);
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
