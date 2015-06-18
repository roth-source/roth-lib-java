package roth.lib.map.json;

import roth.lib.annotation.Property;

public class GenericModelA
{
	@Property(name = "value")
	protected Integer value;
	
	public GenericModelA()
	{
		
	}
	
	public Integer getValue()
	{
		return value;
	}
	
	public GenericModelA setValue(Integer value)
	{
		this.value = value;
		return this;
	}
	
}
