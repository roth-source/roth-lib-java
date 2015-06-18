package roth.lib.map.json;

import roth.lib.annotation.Property;

public class GenericModelB
{
	@Property(name = "value")
	protected Double value;
	
	public GenericModelB()
	{
		
	}
	
	public Double getValue()
	{
		return value;
	}
	
	public GenericModelB setValue(Double value)
	{
		this.value = value;
		return this;
	}
	
}
