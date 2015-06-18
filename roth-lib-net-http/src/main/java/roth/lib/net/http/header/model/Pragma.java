package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Pragma extends Header<Pragma> implements Characters
{
	protected LinkedList<PragmaValue> pragmaValues = new LinkedList<PragmaValue>();
	
	public Pragma()
	{
		
	}
	
	public Pragma(String value)
	{
		this.value = value;
	}
	
	public Pragma addPragmaValue(PragmaValue cacheControlValue)
	{
		pragmaValues.add(cacheControlValue);
		return this;
	}
	
	public LinkedList<PragmaValue> getPragmaValues()
	{
		return pragmaValues;
	}
	
	@Override
	public Pragma deserialize(String value)
	{
		return new Pragma(value);
	}
	
	@Override
	public Pragma parse()
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
		else if(!pragmaValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(PragmaValue pragmaValue : pragmaValues)
			{
				String pragmaValueString = pragmaValue.toString();
				if(pragmaValueString != null)
				{
					builder.append(seperator);
					builder.append(pragmaValueString);
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
