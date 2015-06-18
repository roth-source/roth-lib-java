package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Te extends Header<Te> implements Characters
{
	protected LinkedList<TeValue> teValues = new LinkedList<TeValue>();
	
	public Te()
	{
		
	}
	
	public Te(String value)
	{
		this.value = value;
	}
	
	public Te addTeValue(TeValue acceptEncodingValue)
	{
		teValues.add(acceptEncodingValue);
		return this;
	}
	
	public LinkedList<TeValue> getTeValues()
	{
		return teValues;
	}
	
	@Override
	public Te deserialize(String value)
	{
		return new Te(value);
	}
	
	@Override
	public Te parse()
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
		else if(!teValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(TeValue teValue : teValues)
			{
				String teValueString = teValue.toString();
				if(teValueString != null)
				{
					builder.append(seperator);
					builder.append(teValueString);
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
