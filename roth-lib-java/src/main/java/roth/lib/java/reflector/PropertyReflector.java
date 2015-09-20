package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedList;

import roth.lib.java.util.ReflectionUtil;

public class PropertyReflector
{
	protected Field field;
	protected Type fieldType;
	protected String propertyName;
	protected boolean id;
	protected boolean generated;
	protected String timeFormat;
	protected LinkedList<String> excludes;
	
	public PropertyReflector(Field field, Type fieldType, String propertyName, boolean id, boolean generated, String timeFormat, LinkedList<String> excludes)
	{
		this.field = field;
		this.fieldType = fieldType;
		this.propertyName = propertyName;
		this.id = id;
		this.generated = generated;
		this.timeFormat = timeFormat;
		this.excludes = excludes;
	}
	
	public String getPropertyName()
	{
		return propertyName;
	}
	
	public Field getField()
	{
		return field;
	}
	
	public String getFieldName()
	{
		return field.getName();
	}
	
	public Type getFieldType()
	{
		return fieldType;
	}
	
	public Class<?> getFieldClass()
	{
		return ReflectionUtil.getTypeClass(fieldType);
	}
	
	public boolean isId()
	{
		return id;
	}
	
	public boolean isGenerated()
	{
		return generated;
	}
	
	public String getTimeFormat()
	{
		return timeFormat;
	}
	
	public LinkedList<String> getExcludes()
	{
		return excludes;
	}
	
}
