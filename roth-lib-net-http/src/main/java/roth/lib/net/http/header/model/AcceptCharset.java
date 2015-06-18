package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class AcceptCharset extends Header<AcceptCharset> implements Characters
{
	protected LinkedList<AcceptCharsetValue> acceptCharsetValues = new LinkedList<AcceptCharsetValue>();
	
	public AcceptCharset()
	{
		
	}
	
	public AcceptCharset(String value)
	{
		this.value = value;
	}
	
	public AcceptCharset addAcceptCharsetValue(AcceptCharsetValue acceptCharsetValue)
	{
		acceptCharsetValues.add(acceptCharsetValue);
		return this;
	}
	
	public LinkedList<AcceptCharsetValue> getAcceptCharsetValues()
	{
		return acceptCharsetValues;
	}
	
	@Override
	public AcceptCharset deserialize(String value)
	{
		return new AcceptCharset(value);
	}
	
	@Override
	public AcceptCharset parse()
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
		else if(!acceptCharsetValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(AcceptCharsetValue acceptCharsetValue : acceptCharsetValues)
			{
				String acceptCharsetValueString = acceptCharsetValue.toString();
				if(acceptCharsetValueString != null)
				{
					builder.append(seperator);
					builder.append(acceptCharsetValueString);
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
