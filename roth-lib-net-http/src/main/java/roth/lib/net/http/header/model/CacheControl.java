package roth.lib.net.http.header.model;

import java.util.LinkedList;

import roth.lib.Characters;

public class CacheControl extends Header<CacheControl> implements Characters
{
	protected LinkedList<CacheControlValue> cacheControlValues = new LinkedList<CacheControlValue>();
	
	public CacheControl()
	{
		
	}
	
	public CacheControl(String value)
	{
		this.value = value;
	}
	
	public CacheControl addCacheControlValue(CacheControlValue cacheControlValue)
	{
		cacheControlValues.add(cacheControlValue);
		return this;
	}
	
	public LinkedList<CacheControlValue> getCacheControlValues()
	{
		return cacheControlValues;
	}
	
	@Override
	public CacheControl deserialize(String value)
	{
		return new CacheControl(value);
	}
	
	@Override
	public CacheControl parse()
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
		else if(!cacheControlValues.isEmpty())
		{
			StringBuilder builder = new StringBuilder();
			String seperator = BLANK;
			for(CacheControlValue cacheControlValue : cacheControlValues)
			{
				String cacheControlValueString = cacheControlValue.toString();
				if(cacheControlValueString != null)
				{
					builder.append(seperator);
					builder.append(cacheControlValueString);
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
