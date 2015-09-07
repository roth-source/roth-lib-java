package roth.lib.map.json;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Property;

@Entity
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
