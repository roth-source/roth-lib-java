package roth.lib.java.db.schema;

import java.io.Serializable;
import java.util.Map.Entry;

import roth.lib.java.annotation.Entity;
import roth.lib.java.lang.Map;

@Entity
@SuppressWarnings("serial")
public class EnumSchema implements Serializable
{
	protected String name;
	protected Map<Integer, String> elementCodeNameMap = new Map<Integer, String>();
	protected Map<String, Integer> elementNameCodeMap = new Map<String, Integer>();
	
	protected EnumSchema()
	{
		
	}
	
	public EnumSchema(String name)
	{
		this.name = name;
	}
	
	public String getElementName(int elementCode)
	{
		return elementCodeNameMap.get(elementCode);
	}
	
	public int getElementCode(String elementName)
	{
		return elementNameCodeMap.get(elementName);
	}
	
	public EnumSchema putElement(int elementCode, String elementName)
	{
		elementCodeNameMap.put(elementCode, elementName);
		elementNameCodeMap.put(elementName, elementCode);
		return this;
	}
	
	public EnumSchema removeElement(int elementCode)
	{
		elementCodeNameMap.remove(elementCode);
		for(Entry<String, Integer> entry : elementNameCodeMap.entrySet())
		{
			if(entry.getValue().equals(elementCode))
			{
				elementNameCodeMap.remove(entry.getKey());
				break;
			}
		}
		return this;
	}
	
	public EnumSchema removeElement(String elementName)
	{
		elementNameCodeMap.remove(elementName);
		for(Entry<Integer, String> entry : elementCodeNameMap.entrySet())
		{
			if(entry.getValue().equals(elementName))
			{
				elementCodeNameMap.remove(entry.getKey());
				break;
			}
		}
		return this;
	}
	
}
