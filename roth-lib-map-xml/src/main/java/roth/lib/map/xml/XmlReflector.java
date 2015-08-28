package roth.lib.map.xml;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.accessor.GetPropertyNameAccessor;
import roth.lib.annotation.Property;
import roth.lib.map.MapperConfig;
import roth.lib.map.MapperReflector;
import roth.lib.map.xml.accessor.GetXmlAttributeNameAccessor;
import roth.lib.map.xml.accessor.GetXmlElementsNameAccessor;
import roth.lib.map.xml.accessor.GetXmlPropertyNameAccessor;
import roth.lib.map.xml.accessor.IsXmlAttributesAccessor;
import roth.lib.map.xml.annotation.XmlAttribute;
import roth.lib.map.xml.annotation.XmlAttributes;
import roth.lib.map.xml.annotation.XmlElements;
import roth.lib.map.xml.annotation.XmlProperty;
import roth.lib.map.xml.reflector.XmlAttributeReflector;
import roth.lib.reflector.PropertyReflector;
import roth.lib.util.ReflectionUtil;

public class XmlReflector extends MapperReflector
{
	protected static XmlReflector instance;
	
	protected LinkedList<GetXmlPropertyNameAccessor<? extends Annotation>> getXmlPropertyNameAccessors = new LinkedList<GetXmlPropertyNameAccessor<? extends Annotation>>();
	protected LinkedList<GetXmlElementsNameAccessor<? extends Annotation>> getXmlElementsNameAccessors = new LinkedList<GetXmlElementsNameAccessor<? extends Annotation>>();
	protected LinkedList<GetXmlAttributeNameAccessor<? extends Annotation>> getXmlAttributeNameAccessors = new LinkedList<GetXmlAttributeNameAccessor<? extends Annotation>>();
	protected LinkedList<IsXmlAttributesAccessor<? extends Annotation>> isXmlAttributesAccessors = new LinkedList<IsXmlAttributesAccessor<? extends Annotation>>();
	
	protected LinkedHashMap<Type, LinkedList<XmlAttributeReflector>> xmlAttributeReflectorsMap = new LinkedHashMap<Type, LinkedList<XmlAttributeReflector>>();
	protected LinkedHashMap<Type, Field> xmlAttributesFieldMap = new LinkedHashMap<Type, Field>();
	
	public XmlReflector()
	{
		super();
		addGetPropertyNameAccessor(new GetPropertyNameAccessor<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Property property)
			{
				if(property.xml())
				{
					if(isValid(property.xmlName()))
					{
						return property.xmlName();
					}
					else if(isValid(property.name()))
					{
						return property.name();
					}
				}
				return null;
			}
		});
		addGetXmlPropertyNameAccessor(new GetXmlPropertyNameAccessor<XmlProperty>(XmlProperty.class)
		{
			@Override
			public String getXmlPropertyName(XmlProperty xmlProperty)
			{
				return xmlProperty.name();
			}
		});
		addGetXmlElementsNameAccessor(new GetXmlElementsNameAccessor<XmlElements>(XmlElements.class)
		{
			@Override
			public String getXmlElementsName(XmlElements xmlElements)
			{
				return xmlElements.name();
			}
		});
		addGetXmlAttributeNameAccessor(new GetXmlAttributeNameAccessor<XmlAttribute>(XmlAttribute.class)
		{
			@Override
			public String getXmlAttributeName(XmlAttribute xmlAttribute)
			{
				return xmlAttribute.name();
			}
		});
		addIsXmlAttributesAccessor(new IsXmlAttributesAccessor<XmlAttributes>(XmlAttributes.class){});
	}
	
	public static XmlReflector get()
	{
		if(instance == null)
		{
			instance = new XmlReflector();
		}
		return instance;
	}
	
	public static void set(XmlReflector newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public XmlMapper getMapper()
	{
		return new XmlMapper(this);
	}
	
	@Override
	public XmlMapper getMapper(MapperConfig mapperConfig)
	{
		return new XmlMapper(this, mapperConfig);
	}
	
	public XmlReflector addGetXmlPropertyNameAccessor(GetXmlPropertyNameAccessor<? extends Annotation> getXmlPropertyNameAccessor)
	{
		getXmlPropertyNameAccessors.addFirst(getXmlPropertyNameAccessor);
		return this;
	}
	
	public XmlReflector addGetXmlElementsNameAccessor(GetXmlElementsNameAccessor<? extends Annotation> getXmlElementsNameAccessor)
	{
		getXmlElementsNameAccessors.addFirst(getXmlElementsNameAccessor);
		return this;
	}
	
	public XmlReflector addGetXmlAttributeNameAccessor(GetXmlAttributeNameAccessor<? extends Annotation> getXmlAttributeNameAccessor)
	{
		getXmlAttributeNameAccessors.addFirst(getXmlAttributeNameAccessor);
		return this;
	}
	
	public XmlReflector addIsXmlAttributesAccessor(IsXmlAttributesAccessor<? extends Annotation> hasXmlAttributesAccessor)
	{
		isXmlAttributesAccessors.addFirst(hasXmlAttributesAccessor);
		return this;
	}
	
	public LinkedList<GetXmlPropertyNameAccessor<? extends Annotation>> getGetXmlPropertyNameAccessors()
	{
		return getXmlPropertyNameAccessors;
	}
	
	public LinkedList<GetXmlElementsNameAccessor<? extends Annotation>> getGetXmlElementsNameAccessors()
	{
		return getXmlElementsNameAccessors;
	}
	
	public LinkedList<GetXmlAttributeNameAccessor<? extends Annotation>> getGetXmlAttributeNameAccessors()
	{
		return getXmlAttributeNameAccessors;
	}
	
	public LinkedList<IsXmlAttributesAccessor<? extends Annotation>> getIsXmlAttributesAccessors()
	{
		return isXmlAttributesAccessors;
	}
	
	public LinkedHashMap<Type, LinkedList<XmlAttributeReflector>> getXmlAttributeReflectorsMap()
	{
		return xmlAttributeReflectorsMap;
	}
	
	public LinkedHashMap<Type, Field> getXmlAttributesFieldMap()
	{
		return xmlAttributesFieldMap;
	}
	
	public String getXmlPropertyName(Type type)
	{
		for(GetXmlPropertyNameAccessor<? extends Annotation> getXmlPropertyNameAccessor : getGetXmlPropertyNameAccessors())
		{
			String xmlPropertyName = getXmlPropertyNameAccessor.getXmlPropertyName(type);
			if(xmlPropertyName != null)
			{
				return xmlPropertyName;
			}
		}
		return null;
	}
	
	public String getXmlElementsName(Field field)
	{
		for(GetXmlElementsNameAccessor<? extends Annotation> getXmlElementsNameAccessor : getGetXmlElementsNameAccessors())
		{
			String xmlElementsName = getXmlElementsNameAccessor.getXmlElementsName(field);
			if(xmlElementsName != null)
			{
				return xmlElementsName;
			}
		}
		return null;
	}
	
	public String getXmlAttributeName(Field field)
	{
		for(GetXmlAttributeNameAccessor<? extends Annotation> getXmlAttributeNameAccessor : getGetXmlAttributeNameAccessors())
		{
			String xmlAttributeName = getXmlAttributeNameAccessor.getXmlAttributeName(field);
			if(xmlAttributeName != null)
			{
				return xmlAttributeName;
			}
		}
		return null;
	}
	
	public boolean isXmlAttributes(Field field)
	{
		for(IsXmlAttributesAccessor<? extends Annotation> isXmlAttributesAccessor : getIsXmlAttributesAccessors())
		{
			if(isXmlAttributesAccessor.isXmlAttributes(field))
			{
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<XmlAttributeReflector> getXmlAttributeReflectors(Type type)
	{
		LinkedList<XmlAttributeReflector> xmlAttributeReflectors = getXmlAttributeReflectorsMap().get(type);
		if(xmlAttributeReflectors == null)
		{
			xmlAttributeReflectors = new LinkedList<XmlAttributeReflector>();
			for(Field field : ReflectionUtil.getFields(type))
			{
				String xmlAttributeName = getXmlAttributeName(field);
				if(xmlAttributeName != null)
				{
					field.setAccessible(true);
					Type fieldType = ReflectionUtil.getGenericType(type, field.getGenericType());
					xmlAttributeReflectors.add(new XmlAttributeReflector(field, fieldType, xmlAttributeName));
				}
				else if(isXmlAttributes(field))
				{
					field.setAccessible(true);
					getXmlAttributesFieldMap().put(type, field);
				}
			}
			getXmlAttributeReflectorsMap().put(type, xmlAttributeReflectors);
		}
		return xmlAttributeReflectors;
	}
	
	public LinkedHashMap<String, XmlAttributeReflector> getNameXmlAttributeReflectorMap(Type type)
	{
		LinkedHashMap<String, XmlAttributeReflector> nameXmlAttributeReflectorMap = new LinkedHashMap<String, XmlAttributeReflector>();
		for(XmlAttributeReflector xmlAttributeReflector : getXmlAttributeReflectors(type))
		{
			nameXmlAttributeReflectorMap.put(xmlAttributeReflector.getXmlAttributeName(), xmlAttributeReflector);
		}
		return nameXmlAttributeReflectorMap;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getXmlAttributeMap(Object value)
	{
		LinkedHashMap<String, String> xmlAttributeMap = new LinkedHashMap<String, String>();
		if(value != null)
		{
			Class<?> klass = value.getClass();
			for(XmlAttributeReflector xmlAttributeReflector: getXmlAttributeReflectors(klass))
			{
				try
				{
					Object xmlAttributeValue = xmlAttributeReflector.getField().get(value);
					if(xmlAttributeValue != null)
					{
						xmlAttributeMap.put(xmlAttributeReflector.getXmlAttributeName(), xmlAttributeValue.toString());
					}
				}
				catch(Exception e)
				{
					
				}
			}
			Field xmlAttributesField = getXmlAttributesFieldMap().get(klass);
			if(xmlAttributesField != null)
			{
				try
				{
					Object xmlAttributesObject = xmlAttributesField.get(value);
					if(xmlAttributesObject instanceof Map)
					{
						xmlAttributeMap.putAll((Map<String, String>) xmlAttributesObject);
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
		return xmlAttributeMap;
	}
	
	@SuppressWarnings("unchecked")
	public void setAttributes(Object value, LinkedHashMap<String, String> xmlAttributeMap)
	{
		if(value != null && xmlAttributeMap != null && !xmlAttributeMap.isEmpty())
		{
			LinkedHashMap<String, XmlAttributeReflector> nameXmlAttributeReflectorMap = getNameXmlAttributeReflectorMap(value.getClass());
			LinkedHashMap<String, String> remainintXmlAttributeMap = new LinkedHashMap<String, String>();
			for(Entry<String, String> xmlAttributeEntry : xmlAttributeMap.entrySet())
			{
				String xmlAttributeName = xmlAttributeEntry.getKey();
				String xmlAttributeValue = xmlAttributeEntry.getKey();
				XmlAttributeReflector xmlAttributeReflector = nameXmlAttributeReflectorMap.get(xmlAttributeName);
				if(xmlAttributeReflector != null)
				{
					try
					{
						xmlAttributeReflector.getField().set(value, xmlAttributeValue);
					}
					catch(Exception e)
					{
						remainintXmlAttributeMap.put(xmlAttributeName, xmlAttributeValue);
					}
				}
				else
				{
					remainintXmlAttributeMap.put(xmlAttributeName, xmlAttributeValue);
				}
			}
			Field xmlAttributesField = getXmlAttributesFieldMap().get(value.getClass());
			if(xmlAttributesField != null)
			{
				try
				{
					Object xmlAttributesObject = xmlAttributesField.get(value);
					if(xmlAttributesObject instanceof Map)
					{
						((Map<String, String>) xmlAttributesObject).putAll(remainintXmlAttributeMap);
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
	
	@Override
	public String getFlexibleName(String name)
	{
		int index = name.indexOf(":");
		if(index > -1 && index + 1 < name.length())
		{
			name = name.substring(index + 1).toUpperCase();
		}
		return name;
	}

	@Override
	protected PropertyReflector createPropertyReflector(Field field, Type fieldType, String propertyName, boolean id, boolean generated, String timeFormat, LinkedList<String> excludes)
	{
		String xmlPropertyName = getXmlPropertyName(fieldType);
		if(xmlPropertyName != null)
		{
			propertyName = xmlPropertyName;
		}
		return super.createPropertyReflector(field, fieldType, propertyName, id, generated, timeFormat, excludes);
	}
	
}
