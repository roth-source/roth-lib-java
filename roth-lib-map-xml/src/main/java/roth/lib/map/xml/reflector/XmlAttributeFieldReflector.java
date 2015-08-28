package roth.lib.map.xml.reflector;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class XmlAttributeFieldReflector
{
	protected LinkedHashMap<String, Field> xmlAttributeFieldMap = new LinkedHashMap<String, Field>();
	protected LinkedList<Field> xmlAttributesFields = new LinkedList<Field>();
	
	public XmlAttributeFieldReflector()
	{
		
	}
	
	public LinkedHashMap<String, Field> getXmlAttributeFieldMap()
	{
		return xmlAttributeFieldMap;
	}
	
	public LinkedList<Field> getXmlAttributesFields()
	{
		return xmlAttributesFields;
	}
	
	public XmlAttributeFieldReflector addXmlAttributeField(String name, Field field)
	{
		field.setAccessible(true);
		xmlAttributeFieldMap.put(name, field);
		return this;
	}
	
	public XmlAttributeFieldReflector addXmlAttributesField(Field field)
	{
		field.setAccessible(true);
		xmlAttributesFields.add(field);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public void setAttributes(Object entity, Map<String, String> attributeMap)
	{
		if(entity != null && attributeMap != null && !attributeMap.isEmpty())
		{
			Map<String, String> xmlAttributes = null;
			Field xmlAttributesField = xmlAttributesFields.peekFirst();
			if(xmlAttributesField != null)
			{
				try
				{
					Object xmlAttributesObject = xmlAttributesField.get(entity);
					if(xmlAttributesObject instanceof Map)
					{
						xmlAttributes = (Map<String, String>) xmlAttributesObject;
					}
				}
				catch(Exception e)
				{
					
				}
			}
			for(Entry<String, String> attributeEntry : attributeMap.entrySet())
			{
				String name = attributeEntry.getKey();
				String value = attributeEntry.getValue();
				Field xmlAttributeField = xmlAttributeFieldMap.get(name);
				if(xmlAttributeField != null)
				{
					try
					{
						xmlAttributeField.set(entity, value);
					}
					catch(Exception e)
					{
						
					}
				}
				else if(xmlAttributes != null)
				{
					xmlAttributes.put(name, value);
				}
			}
		}
	}
	
}
