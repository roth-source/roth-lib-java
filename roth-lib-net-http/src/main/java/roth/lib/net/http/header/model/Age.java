package roth.lib.net.http.header.model;

import roth.lib.Characters;
import roth.lib.util.NumberUtil;

public class Age extends Header<Age> implements Characters
{
	protected Integer age;
	
	public Age()
	{
		
	}
	
	public Age(String value)
	{
		this.value = value;
	}
	
	public Age(Integer age)
	{
		this.age = age;
	}
	
	public Age setAge(Integer age)
	{
		this.age = age;
		return this;
	}
	
	public Integer getAge()
	{
		return age;
	}
	
	@Override
	public Age deserialize(String value)
	{
		return new Age(value);
	}
	
	@Override
	public Age parse()
	{
		age = NumberUtil.parseInteger(value);
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else
		{
			StringBuilder builder = new StringBuilder();
			builder.append(age);
			return builder.toString();
		}
	}
	
}
