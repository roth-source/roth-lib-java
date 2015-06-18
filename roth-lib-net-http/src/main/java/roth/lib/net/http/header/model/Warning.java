package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class Warning extends Header<Warning> implements Characters
{
	protected Integer code;
	protected String text;
	
	public Warning()
	{
		
	}
	
	public Warning(String value)
	{
		this.value = value;
	}
	
	public Warning(Integer code, String text)
	{
		this.code = code;
		this.text = text;
	}
	
	public Integer getCode()
	{
		return code;
	}
	
	public String getText()
	{
		return text;
	}
	
	@Override
	public Warning deserialize(String value)
	{
		return new Warning(value);
	}
	
	@Override
	public Warning parse()
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
		else if(code != null && text != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(code);
			builder.append(SPACE);
			builder.append(text);
			return builder.toString();
		}
		return null;
	}
	
}
