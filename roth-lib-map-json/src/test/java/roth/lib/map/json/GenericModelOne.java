package roth.lib.map.json;

import roth.lib.annotation.Property;

public class GenericModelOne<T>
{
	@Property(name = "value")
	protected Boolean value;
	
	@Property(name = "model")
	protected T model;
	
	public GenericModelOne()
	{
		
	}
	
	public Boolean getValue()
	{
		return value;
	}
	
	public T getModel()
	{
		return model;
	}
	
	public GenericModelOne<T> setValue(Boolean value)
	{
		this.value = value;
		return this;
	}
	
	public GenericModelOne<T> setModel(T model)
	{
		this.model = model;
		return this;
	}
	
}
