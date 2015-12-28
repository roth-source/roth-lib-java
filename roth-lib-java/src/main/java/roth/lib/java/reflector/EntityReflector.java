package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import roth.lib.java.annotation.Attributes;
import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Properties;
import roth.lib.java.annotation.Property;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.util.AnnotationUtil;
import roth.lib.java.util.ReflectionUtil;

public class EntityReflector
{
	protected Type type;
	protected Entity entity;
	protected LinkedList<PropertyReflector> propertyReflectors;
	protected PropertiesReflector propertiesReflector;
	protected AttributesReflector attributesReflector;
	
	public EntityReflector(Type type, Entity entity)
	{
		this.type = type;
		this.entity = entity;
	}
	
	protected void init()
	{
		if(propertyReflectors == null)
		{
			propertyReflectors = new LinkedList<PropertyReflector>();
			for(Field field : ReflectionUtil.getFields(type))
			{
				Property property = field.getDeclaredAnnotation(Property.class);
				if(property != null)
				{
					propertyReflectors.add(new PropertyReflector(this, field, property));
				}
				else
				{
					Properties properties = field.getDeclaredAnnotation(Properties.class);
					if(properties != null)
					{
						propertiesReflector = new PropertiesReflector(this, field, properties);
					}
					else
					{
						Attributes attributes = field.getDeclaredAnnotation(Attributes.class);
						if(attributes != null)
						{
							attributesReflector = new AttributesReflector(this, field, attributes);
						}
					}
				}
			}
		}
	}
	
	public Type getType()
	{
		return type;
	}
	
	public Entity getEntity()
	{
		return entity;
	}
	
	public String getEntityName()
	{
		return AnnotationUtil.validate(entity.name());
	}
	
	public String getPropertyName()
	{
		return AnnotationUtil.validate(entity.propertyName());
	}
	
	public LinkedList<PropertyReflector> getPropertyReflectors(MapperType mapperType)
	{
		init();
		LinkedList<PropertyReflector> propertyReflectors = new LinkedList<PropertyReflector>();
		for(PropertyReflector propertyReflector : this.propertyReflectors)
		{
			if(!propertyReflector.isAttribute() && propertyReflector.isProperty(mapperType))
			{
				propertyReflectors.add(propertyReflector);
			}
		}
		return propertyReflectors;
	}
	
	public LinkedList<PropertyReflector> getAttributeReflectors(MapperType mapperType)
	{
		init();
		LinkedList<PropertyReflector> attributeReflectors = new LinkedList<PropertyReflector>();
		for(PropertyReflector propertyReflector : this.propertyReflectors)
		{
			if(propertyReflector.isAttribute() && propertyReflector.isProperty(mapperType))
			{
				attributeReflectors.add(propertyReflector);
			}
		}
		return attributeReflectors;
	}
	
	public LinkedList<PropertyReflector> getIdReflectors(MapperType mapperType)
	{
		init();
		LinkedList<PropertyReflector> idReflectors = new LinkedList<PropertyReflector>();
		for(PropertyReflector propertyReflector : this.propertyReflectors)
		{
			if(propertyReflector.isId() && propertyReflector.isProperty(mapperType))
			{
				idReflectors.add(propertyReflector);
			}
		}
		return idReflectors;
	}
	
	public LinkedList<PropertyReflector> getGeneratedReflectors(MapperType mapperType)
	{
		init();
		LinkedList<PropertyReflector> generatedReflectors = new LinkedList<PropertyReflector>();
		for(PropertyReflector propertyReflector : this.propertyReflectors)
		{
			if(propertyReflector.isGenerated() && propertyReflector.isProperty(mapperType))
			{
				generatedReflectors.add(propertyReflector);
			}
		}
		return generatedReflectors;
	}
	
	public PropertiesReflector getPropertiesReflector()
	{
		init();
		return propertiesReflector;
	}
	
	public AttributesReflector getAttributesReflector()
	{
		init();
		return attributesReflector;
	}
	
	public PropertyReflector getPropertyReflector(String name, MapperType mapperType, MapperReflector mapperReflector)
	{
		return getPropertyReflector(name, mapperType, mapperReflector, false);
	}
	
	public PropertyReflector getAttributeReflector(String name, MapperType mapperType, MapperReflector mapperReflector)
	{
		return getPropertyReflector(name, mapperType, mapperReflector, true);
	}
	
	public PropertyReflector getPropertyReflector(String name, MapperType mapperType, MapperReflector mapperReflector, boolean attribute)
	{
		PropertyReflector reflector = null;
		if(name != null)
		{
			name = stripNamespace(name, mapperType);
			for(PropertyReflector propertyReflector : getPropertyReflectors(mapperType))
			{
				if((!attribute && !propertyReflector.isAttribute()) || (attribute && propertyReflector.isAttribute()))
				{
					String propertyName = stripNamespace(propertyReflector.getPropertyName(mapperType), mapperType);
					if(name.equalsIgnoreCase(propertyName))
					{
						reflector = propertyReflector;
						break;
					}
				}
			}
			if(reflector == null)
			{
				for(PropertyReflector propertyReflector : getPropertyReflectors(mapperType))
				{
					if(!attribute && !propertyReflector.isAttribute())
					{
						EntityReflector entityReflector = mapperReflector.getEntityReflector(propertyReflector.getFieldType());
						if(entityReflector != null)
						{
							String propertyName = stripNamespace(entityReflector.getPropertyName(), mapperType);
							if(name.equalsIgnoreCase(propertyName))
							{
								reflector = propertyReflector;
								break;
							}
						}
					}
				}
			}
		}
		return reflector;
	}
	
	public String stripNamespace(String name, MapperType type)
	{
		if(name != null && MapperType.XML.equals(type))
		{
			int index = name.indexOf(":");
			if(index > -1 && index + 1 < name.length())
			{
				return name.substring(index + 1);
			}
		}
		return name;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getAttributeMap(Object value, MapperType mapperType)
	{
		LinkedHashMap<String, String> attributeMap = new LinkedHashMap<String, String>();
		if(value != null)
		{
			AttributesReflector attributesReflector = getAttributesReflector();
			if(attributesReflector != null)
			{
				try
				{
					Object attributesObject = attributesReflector.getField().get(value);
					if(attributesObject instanceof Map)
					{
						attributeMap.putAll((Map<String, String>) attributesObject);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			for(PropertyReflector propertyReflector : getAttributeReflectors(mapperType))
			{
				try
				{
					Object attributeValue = propertyReflector.getField().get(value);
					if(attributeValue != null)
					{
						attributeMap.put(propertyReflector.getPropertyName(mapperType), attributeValue.toString());
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return attributeMap;
	}
	
	@SuppressWarnings("unchecked")
	public void setAttributeMap(Object value, MapperType mapperType, LinkedHashMap<String, String> attributeMap)
	{
		if(value != null && attributeMap != null && !attributeMap.isEmpty())
		{
			attributeMap = new LinkedHashMap<String, String>(attributeMap);
			for(PropertyReflector attributeReflector : getAttributeReflectors(mapperType))
			{
				String attributeName = attributeReflector.getPropertyName(mapperType);
				String attributeValue = attributeMap.get(attributeName);
				try
				{
					attributeReflector.getField().set(value, attributeValue);
					attributeMap.remove(attributeName);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			Field attributesField = getAttributesReflector().getField();
			if(attributesField != null)
			{
				try
				{
					Object attributesObject = attributesField.get(value);
					if(attributesObject instanceof Map)
					{
						((Map<String, String>) attributesObject).putAll(attributeMap);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
}
