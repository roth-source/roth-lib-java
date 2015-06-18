package roth.lib.map.xml;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import roth.lib.map.xml.annotation.XmlAttributes;

@SuppressWarnings("serial")
public class XmlModel implements Serializable
{
	@XmlAttributes
	protected LinkedHashMap<String, String> attributeMap = new LinkedHashMap<String, String>();
	
	public XmlModel()
	{
		
	}
	
	public LinkedHashMap<String, String> getAttributeMap()
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
