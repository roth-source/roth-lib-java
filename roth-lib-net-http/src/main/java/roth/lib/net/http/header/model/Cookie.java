package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class Cookie extends Header<Cookie> implements Characters
{
	protected LinkedList<CookieValue> cookieValues = new LinkedList<CookieValue>();
	
	public Cookie()
	{
		
	}
	
	public Cookie(String value)
	{
		this.value = value;
	}
	
	public Cookie addCookieValue(CookieValue cookieValue)
	{
		cookieValues.add(cookieValue);
		return this;
	}
	
	public LinkedList<CookieValue> getCookieValues()
	{
		return cookieValues;
	}
	
	@Override
	public Cookie deserialize(String value)
	{
		return new Cookie(value);
	}
	
	@Override
	public Cookie parse()
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
		else if(!cookieValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(CookieValue cookieValue : cookieValues)
			{
				String cookieValueString = cookieValue.toString();
				if(cookieValueString != null)
				{
					builder.append(seperator);
					builder.append(cookieValueString);
					if(BLANK.equals(seperator))
					{
						seperator += SEMI_COLON;
						seperator += SPACE;
					}
				}
			}
			return builder.toString();
		}
		return null;
	}
	
}
