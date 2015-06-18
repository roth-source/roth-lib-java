package roth.lib.map;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import roth.lib.annotation.Entity;
import roth.lib.annotation.Id;
import roth.lib.map.mapper.EntityMapper;
import roth.lib.map.mapper.IdMapper;
import roth.lib.map.mapper.PropertyMapper;
import roth.lib.map.util.MapperUtil;

public abstract class Mapper
{
	public static Charset UTF_8 = Charset.forName("UTF-8");
	
	protected LinkedList<EntityMapper<? extends Annotation>> entityMappers = new LinkedList<EntityMapper<? extends Annotation>>();
	protected LinkedList<PropertyMapper<? extends Annotation>> propertyMappers = new LinkedList<PropertyMapper<? extends Annotation>>();
	protected LinkedList<IdMapper<? extends Annotation>> idMappers = new LinkedList<IdMapper<? extends Annotation>>();
	protected LinkedHashMap<Type, LinkedList<PropertyField>> propertyFieldMap = new LinkedHashMap<Type, LinkedList<PropertyField>>();
	
	protected Mapper()
	{
		entityMappers.add(new EntityMapper<Entity>(Entity.class)
		{
			@Override
			public String getEntityName(Entity entity)
			{
				if(entity != null)
				{
					if(isValid(entity.name()))
					{
						return entity.name();
					}
					else if(isValid(entity.value()))
					{
						return entity.value();
					}
				}
				return null;
			}
		});
		idMappers.add(new IdMapper<Id>(Id.class)
		{
			@Override
			public boolean isGenerated(Id id)
			{
				if(id != null)
				{
					return id.generated();
				}
				return false;
			}
		});
	}
	
	public void addEntityMapper(EntityMapper<? extends Annotation> entityMapper)
	{
		entityMappers.addFirst(entityMapper);
	}
	
	public void addPropertyMapper(PropertyMapper<? extends Annotation> propertyMapper)
	{
		propertyMappers.addFirst(propertyMapper);
	}
	
	public void addIdMapper(IdMapper<? extends Annotation> idMapper)
	{
		idMappers.addFirst(idMapper);
	}
	
	public String getEntityName(Type type)
	{
		for(EntityMapper<? extends Annotation> entityMapper : entityMappers)
		{
			String entityName = entityMapper.getEntityNameFromClass(type);
			if(entityName != null)
			{
				return entityName;
			}
		}
		return null;
	}
	
	public String getPropertyName(Field field)
	{
		for(PropertyMapper<? extends Annotation> propertyMapper : propertyMappers)
		{
			String propertyName = propertyMapper.getPropertyNameFromField(field);
			if(propertyName != null)
			{
				return propertyName;
			}
		}
		return null;
	}
	
	public boolean isId(Field field)
	{
		for(IdMapper<? extends Annotation> idMapper : idMappers)
		{
			if(idMapper.hasId(field))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isIdGenerated(Field field)
	{
		for(IdMapper<? extends Annotation> idMapper : idMappers)
		{
			boolean generated = idMapper.isGeneratedFromField(field);
			if(generated)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isEntityName(Field field)
	{
		for(PropertyMapper<? extends Annotation> propertyMapper : propertyMappers)
		{
			Boolean entityName = propertyMapper.isEntityNameFromField(field);
			if(entityName != null)
			{
				return entityName;
			}
		}
		return false;
	}
	
	public LinkedHashMap<String, PropertyField> getPropertyNameFieldMap(Type type)
	{
		LinkedHashMap<String, PropertyField> propertyNameFieldMap = new LinkedHashMap<String, PropertyField>();
		for(PropertyField propertyField : getPropertyFields(type))
		{
			propertyNameFieldMap.put(propertyField.getPropertyName(), propertyField);
		}
		return propertyNameFieldMap;
	}
	
	public LinkedHashMap<String, PropertyField> getFieldNameFieldMap(Type type)
	{
		LinkedHashMap<String, PropertyField> fieldNameFieldMap = new LinkedHashMap<String, PropertyField>();
		for(PropertyField propertyField : getPropertyFields(type))
		{
			fieldNameFieldMap.put(propertyField.getFieldName(), propertyField);
		}
		return fieldNameFieldMap;
	}
	
	public LinkedList<PropertyField> getPropertyFields(Type type)
	{
		LinkedList<PropertyField> propertyFields = propertyFieldMap.get(type);
		if(propertyFields == null)
		{
			propertyFields = new LinkedList<PropertyField>();
			for(Field field : getFields(type))
			{
				String propertyName = getPropertyName(field);
				if(propertyName != null)
				{
					boolean entityName = isEntityName(field);
					boolean id = false;
					boolean generated = false;
					if(isId(field))
					{
						id = true;
						if(isIdGenerated(field))
						{
							generated = true;
						}
					}
					field.setAccessible(true);
					Type fieldType = MapperUtil.getGenericType(type, field.getGenericType());
					if(entityName)
					{
						String propertyEntityName = getEntityName(fieldType);
						if(propertyEntityName != null)
						{
							propertyName = propertyEntityName;
						}
					}
					propertyFields.add(new PropertyField(propertyName, field, fieldType, entityName, id, generated));
				}
			}
			propertyFieldMap.put(type, propertyFields);
		}
		return propertyFields;
	}
	
	public boolean hasPropertyFields(Type type)
	{
		return !getPropertyFields(type).isEmpty();
	}
	
	public LinkedList<Field> getFields(Type type)
	{
		if(type == null)
		{
			return new LinkedList<Field>();
		}
		else
		{
			Class<?> klass = MapperUtil.getClass(type);
			LinkedList<Field> fields = getFields(klass.getSuperclass());
			for(Field field : Arrays.asList(klass.getDeclaredFields()))
			{
				if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
				{
					fields.add(field);
				}
			}
			return fields;
		}
	}
	
	public LinkedList<PropertyField> getIdPropertyFields(Type type)
	{
		LinkedList<PropertyField> idPropertyFields = new LinkedList<PropertyField>();
		for(PropertyField propertyField : getPropertyFields(type))
		{
			if(propertyField.isId())
			{
				idPropertyFields.add(propertyField);
			}
		}
		return idPropertyFields;
	}
	
	public Object getPropertyObject(Field field, Object object)
	{
		Object propertyObject = null;
		try
		{
			propertyObject = field.get(object);
		}
		catch(IllegalAccessException e)
		{
			
		}
		return propertyObject;
	}
	
	public boolean isEntity(Type type)
	{
		return hasPropertyFields(type);
	}
	
	public boolean isArray(Type type)
	{
		Class<?> klass = MapperUtil.getClass(type);
		return klass.isArray();
	}
	
	public boolean isCollection(Type type)
	{
		Class<?> klass = MapperUtil.getClass(type);
		return Collection.class.isAssignableFrom(klass);
	}
	
	public boolean isMap(Type type)
	{
		Class<?> klass = MapperUtil.getClass(type);
		return Map.class.isAssignableFrom(klass);
	}
	
	public boolean isSerializable(Type type, Config config)
	{
		Class<?> klass = MapperUtil.getClass(type);
		return config.hasSerializer(klass);
	}
	
	public Class<?> getElementClass(Type type)
	{
		Class<?> elementClass = Object.class;
		Class<?> klass = MapperUtil.getClass(type);
		if(isArray(klass))
		{
			elementClass = klass.getComponentType();
		}
		else if(isCollection(klass))
		{
			if(type instanceof ParameterizedType)
			{
				elementClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
			}
		}
		else if(isMap(klass))
		{
			if(type instanceof ParameterizedType)
			{
				elementClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[1];
			}
		}
		return elementClass;
	}
	
	public LinkedList<?> asCollection(Object value)
	{
		return (value instanceof Collection) ? new LinkedList<Object>((Collection<?>) value) : new LinkedList<Object>(Arrays.asList((Object[]) value));
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, ?> asMap(Object value)
	{
		return (value instanceof Map) ? new LinkedHashMap<String, Object>((Map<String, ?>) value) : new LinkedHashMap<String, Object>();
	}
	
}
