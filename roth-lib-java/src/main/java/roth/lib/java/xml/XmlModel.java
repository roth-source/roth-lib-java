package roth.lib.java.xml;

import java.io.Serializable;

import roth.lib.java.annotation.Attributes;
import roth.lib.java.annotation.Entity;
import roth.lib.java.lang.Map;

@Entity
@SuppressWarnings("serial")
public class XmlModel implements Serializable
{
	@Attributes
	protected Map<String, String> attributeMap = new Map<String, String>();
	
	public XmlModel()
	{
		
	}
	
	public Map<String, String> getAttributeMap()
	{
		return attributeMap;
	}
	
	public XmlModel setAttribute(String name, String value)
	{
		attributeMap.put(name, value);
		return this;
	}
	
	public XmlModel setAttributeS(Map<String, String> map)
	{
		attributeMap.putAll(map);
		return this;
	}
	
}
