package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class AcceptEncoding extends Header<AcceptEncoding> implements Characters
{
	protected LinkedList<AcceptEncodingValue> acceptEncodingValues = new LinkedList<AcceptEncodingValue>();
	
	public AcceptEncoding()
	{
		
	}
	
	public AcceptEncoding(String value)
	{
		this.value = value;
	}
	
	public AcceptEncoding addAcceptEncodingValue(AcceptEncodingValue acceptEncodingValue)
	{
		acceptEncodingValues.add(acceptEncodingValue);
		return this;
	}
	
	public LinkedList<AcceptEncodingValue> getAcceptEncodingValues()
	{
		return acceptEncodingValues;
	}
	
	@Override
	public AcceptEncoding deserialize(String value)
	{
		return new AcceptEncoding(value);
	}
	
	@Override
	public AcceptEncoding parse()
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
		else if(!acceptEncodingValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(AcceptEncodingValue acceptEncodingValue : acceptEncodingValues)
			{
				String acceptEncodingValueString = acceptEncodingValue.toString();
				if(acceptEncodingValueString != null)
				{
					builder.append(seperator);
					builder.append(acceptEncodingValueString);
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
