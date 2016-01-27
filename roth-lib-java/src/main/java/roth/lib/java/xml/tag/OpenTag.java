package roth.lib.java.xml.tag;

import roth.lib.java.lang.Map;
import java.util.Map.Entry;

public class OpenTag extends Tag
{
	protected String name;
	protected Map<String, String> attributeMap = new Map<String, String>();
	
	public OpenTag(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Map<String, String> getAttributeMap()
	{
		return attributeMap;
	}
	
	public OpenTag setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public OpenTag addAttribute(String name, String value)
	{
		attributeMap.put(name, value);
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(LEFT_ANGLE_BRACKET);
		builder.append(name);
		for(Entry<String, String> attributeEntry : attributeMap.entrySet())
		{
			builder.append(SPACE);
			builder.append(attributeEntry.getKey());
			builder.append(EQUAL);
			builder.append(QUOTE);
			builder.append(attributeEntry.getValue());
			builder.append(QUOTE);
		}
		builder.append(RIGHT_ANGLE_BRACKET);
		return builder.toString();
	}
	
}
