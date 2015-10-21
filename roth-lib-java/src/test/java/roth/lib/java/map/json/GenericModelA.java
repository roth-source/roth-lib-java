package roth.lib.java.map.json;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

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
