package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import roth.lib.java.util.ReflectionUtil;

public class PropertiesReflector
{
	protected EntityReflector entityReflector;
	protected Field field;
	protected Type fieldType;
	
	public PropertiesReflector(EntityReflector entityReflector, Field field)
	{
		field.setAccessible(true);
		this.entityReflector = entityReflector;
		this.field = field;
		this.fieldType = ReflectionUtil.getGenericType(entityReflector.getType(), field.getGenericType());
	}
	
	public EntityReflector getEntityReflector()
	{
		return entityReflector;
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
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(Object value)
	{
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			Object fieldValue = getField().get(value);
			if(fieldValue instanceof Map)
			{
				map = (Map<String, Object>) fieldValue;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	
	public void put(Object model, String name, Object value)
	{
		getMap(model).put(name, value);
	}
	
}
