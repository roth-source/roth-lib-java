package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Via extends Header<Via> implements Characters
{
	protected LinkedList<ViaValue> viaValues = new LinkedList<ViaValue>();
	
	public Via()
	{
		
	}
	
	public Via(String value)
	{
		this.value = value;
	}
	
	public Via addViaValue(ViaValue acceptValue)
	{
		viaValues.add(acceptValue);
		return this;
	}
	
	public LinkedList<ViaValue> getViaValues()
	{
		return viaValues;
	}
	
	@Override
	public Via deserialize(String value)
	{
		return new Via(value);
	}
	
	@Override
	public Via parse()
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
		else if(!viaValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(ViaValue viaValue : viaValues)
			{
				String viaValueString = viaValue.toString();
				if(viaValueString != null)
				{
					builder.append(seperator);
					builder.append(viaValueString);
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
