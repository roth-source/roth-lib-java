package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import roth.lib.java.util.ReflectionUtil;

public class PropertiesReflector
{
	protected EntityReflector entityReflector;
	protected Field field;
	protected Type type;
	
	public PropertiesReflector(EntityReflector entityReflector, Field field)
	{
		field.setAccessible(true);
		this.entityReflector = entityReflector;
		this.field = field;
		this.type = ReflectionUtil.getGenericType(entityReflector.getType(), field.getGenericType());
	}
	
}
