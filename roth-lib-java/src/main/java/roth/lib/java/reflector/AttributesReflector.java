package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import roth.lib.java.annotation.Attributes;
import roth.lib.java.util.ReflectionUtil;

public class AttributesReflector
{
	protected EntityReflector entityReflector;
	protected Field field;
	protected Type fieldType;
	protected Attributes attributes;
	
	public AttributesReflector(EntityReflector entityReflector, Field field, Attributes attributes)
	{
		field.setAccessible(true);
		this.entityReflector = entityReflector;
		this.field = field;
		this.fieldType = ReflectionUtil.getGenericType(entityReflector.getType(), field.getGenericType());
		this.attributes = attributes;
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
	
	public Attributes getAttributes()
	{
		return attributes;
	}
	
}
