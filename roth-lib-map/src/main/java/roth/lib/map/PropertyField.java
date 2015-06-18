package roth.lib.map;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import roth.lib.map.util.MapperUtil;

public class PropertyField
{
	protected String propertyName;
	protected Field field;
	protected Type fieldType;
	protected boolean entityName;
	protected boolean id;
	protected boolean generated;
	
	public PropertyField(String propertyName, Field field, Type fieldType, boolean entityName, boolean id, boolean generated)
	{
		this.propertyName = propertyName;
		this.field = field;
		this.fieldType = fieldType;
		this.entityName = entityName;
		this.id = id;
		this.generated = generated;
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
		return MapperUtil.getClass(fieldType);
	}
	
	public boolean isId()
	{
		return id;
	}
	
	public boolean isGenerated()
	{
		return generated;
	}
	
	public boolean isEntityName()
	{
		return entityName;
	}
	
}
