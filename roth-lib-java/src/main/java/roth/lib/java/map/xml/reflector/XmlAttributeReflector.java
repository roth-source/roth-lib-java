package roth.lib.java.map.xml.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class XmlAttributeReflector
{
	protected Field field;
	protected Type fieldType;
	protected String xmlAttributeName;
	
	public XmlAttributeReflector(Field field, Type fieldType, String xmlAttributeName)
	{
		this.field = field;
		this.fieldType = fieldType;
		this.xmlAttributeName = xmlAttributeName;
	}
	
	public Field getField()
	{
		return field;
	}
	
	public Type getFieldType()
	{
		return fieldType;
	}
	
	public String getXmlAttributeName()
	{
		return xmlAttributeName;
	}
	
}
