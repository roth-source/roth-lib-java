package roth.lib.net.http.header.model;

import roth.lib.Characters;
import roth.lib.util.NumberUtil;

public class MaxForwards extends Header<MaxForwards> implements Characters
{
	protected Integer maxForwards;
	
	public MaxForwards()
	{
		
	}
	
	public MaxForwards(String value)
	{
		this.value = value;
	}
	
	public MaxForwards(Integer maxForwards)
	{
		this.maxForwards = maxForwards;
	}
	
	public Integer getMaxForwards()
	{
		return maxForwards;
	}
	
	@Override
	public MaxForwards deserialize(String value)
	{
		return new MaxForwards(value);
	}
	
	@Override
	public MaxForwards parse()
	{
		maxForwards = NumberUtil.parseInteger(value);
		return this;
	}
	
	@Override
	public String toString()
	{

		if(value != null)
		{
			return value;
		}
		else if(maxForwards != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(maxForwards);
			return builder.toString();
		}
		return null;
	}
	
}
