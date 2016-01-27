package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import roth.lib.java.annotation.Properties;
import roth.lib.java.lang.Map;
import roth.lib.java.util.ReflectionUtil;

public class PropertiesReflector
{
	protected EntityReflector entityReflector;
	protected Field field;
	protected Type fieldType;
	protected Properties properties;
	
	public PropertiesReflector(EntityReflector entityReflector, Field field, Properties properties)
	{
		field.setAccessible(true);
		this.entityReflector = entityReflector;
		this.field = field;
		this.fieldType = ReflectionUtil.getGenericType(entityReflector.getType(), field.getGenericType());
		this.properties = properties;
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
	
	public Properties getProperties()
	{
		return properties;
	}
	
	public boolean isFirst()
	{
		return properties.first();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(Object value)
	{
		Map<String, Object> map = new Map<String, Object>();
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
