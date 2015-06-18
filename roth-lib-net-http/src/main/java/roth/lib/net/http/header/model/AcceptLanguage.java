package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class AcceptLanguage extends Header<AcceptLanguage> implements Characters
{
	protected LinkedList<AcceptLanguageValue> acceptLanguageValues = new LinkedList<AcceptLanguageValue>();
	
	public AcceptLanguage()
	{
		
	}
	
	public AcceptLanguage(String value)
	{
		this.value = value;
	}
	
	public AcceptLanguage addAcceptLanguageValue(AcceptLanguageValue acceptLanguageValue)
	{
		acceptLanguageValues.add(acceptLanguageValue);
		return this;
	}
	
	public LinkedList<AcceptLanguageValue> getAcceptLanguageValues()
	{
		return acceptLanguageValues;
	}
	
	@Override
	public AcceptLanguage deserialize(String value)
	{
		return new AcceptLanguage(value);
	}
	
	@Override
	public AcceptLanguage parse()
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
		else if(!acceptLanguageValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(AcceptLanguageValue acceptLanguageValue : acceptLanguageValues)
			{
				String acceptLanguageValueString = acceptLanguageValue.toString();
				if(acceptLanguageValueString != null)
				{
					builder.append(seperator);
					builder.append(acceptLanguageValueString);
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
