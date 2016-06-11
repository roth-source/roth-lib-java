package roth.lib.java.xml;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;

@Entity
@SuppressWarnings("serial")
public class XmlValue<T> extends XmlModel
{
	@Property(name = "value")
	protected T value;
	
	public XmlValue()
	{
		
	}
	
	public T getValue()
	{
		return value;
	}
	
	public XmlValue<T> setValue(T value)
	{
		this.value = value;
		return this;
	}
	
}
