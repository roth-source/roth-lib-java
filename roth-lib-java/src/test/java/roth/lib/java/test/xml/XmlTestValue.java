package roth.lib.java.test.xml;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.xml.XmlValue;

@Entity
@SuppressWarnings("serial")
public class XmlTestValue extends XmlValue<String>
{
	@Property(name = "type", attribute = true)
	protected String type;
	
	public XmlTestValue()
	{
		
	}
	
	public String getType()
	{
		return type;
	}
	
	public XmlTestValue setType(String type)
	{
		this.type = type;
		return this;
	}
	
}
