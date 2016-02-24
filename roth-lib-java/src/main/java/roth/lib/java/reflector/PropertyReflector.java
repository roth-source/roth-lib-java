package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import roth.lib.java.annotation.Property;
import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.util.AnnotationUtil;
import roth.lib.java.util.ReflectionUtil;

public class PropertyReflector
{
	protected EntityReflector entityReflector;
	protected Field field;
	protected Type fieldType;
	protected Property property;
	
	public PropertyReflector(EntityReflector entityReflector, Field field, Property property)
	{
		field.setAccessible(true);
		this.entityReflector = entityReflector;
		this.field = field;
		this.fieldType = ReflectionUtil.getGenericType(entityReflector.getType(), field.getGenericType());
		this.property = property;
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
	
	public Property getProperty()
	{
		return property;
	}
	
	public boolean isProperty(MapperType mapperType)
	{
		boolean property = true;
		switch(mapperType)
		{
			case MYSQL:
			{
				property = isMysql();
				if(property)
				{
					property = isDb();
				}
				break;
			}
			case JSON:
			{
				property = isJson();
				if(property)
				{
					property = isSerial();
				}
				break;
			}
			case XML:
			{
				property = isXml();
				if(property)
				{
					property = isSerial();
				}
				break;
			}
			case FORM:
			{
				property = isForm();
				if(property)
				{
					property = isSerial();
				}
				break;
			}
			case TABLE:
			{
				property = isTable();
				if(property)
				{
					property = isSerial();
				}
				break;
			}
		}
		return property;
	}
	
	public String getPropertyName(MapperType mapperType)
	{
		String name = null;
		switch(mapperType)
		{
			case MYSQL:
			{
				name = getMysqlName();
				if(name == null)
				{
					name = getDbName();
				}
				break;
			}
			case JSON:
			{
				name = getJsonName();
				if(name == null)
				{
					name = getSerialName();
				}
				break;
			}
			case XML:
			{
				name = getXmlName();
				if(name == null)
				{
					name = getSerialName();
				}
				break;
			}
			case FORM:
			{
				name = getFormName();
				if(name == null)
				{
					name = getSerialName();
				}
				break;
			}
			case TABLE:
			{
				name = getTableName();
				if(name == null)
				{
					name = getSerialName();
				}
				break;
			}
		}
		if(name == null)
		{
			name = getName();
		}
		return name;
	}
	
	public Serializer<?> getSerializer(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		return getSerializer(mapperType, mapperReflector, mapperConfig, getFieldClass());
	}
	
	public Serializer<?> getSerializer(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig, Class<?> klass)
	{
		Serializer<?> serializer = null;
		try
		{
			switch(mapperType)
			{
				case JSON:
				{
					Class<?> serializerClass = property.jsonSerializer();
					if(!Void.class.equals(serializerClass))
					{
						serializer = (Serializer<?>) serializerClass.newInstance();
					}
					break;
				}
				case XML:
				{
					Class<?> serializerClass = property.xmlSerializer();
					if(!Void.class.equals(serializerClass))
					{
						serializer = (Serializer<?>) serializerClass.newInstance();
					}
					break;
				}
				case FORM:
				{
					Class<?> serializerClass = property.formSerializer();
					if(!Void.class.equals(serializerClass))
					{
						serializer = (Serializer<?>) serializerClass.newInstance();
					}
					break;
				}
				case TABLE:
				{
					Class<?> serializerClass = property.tableSerializer();
					if(!Void.class.equals(serializerClass))
					{
						serializer = (Serializer<?>) serializerClass.newInstance();
					}
					break;
				}
				case MYSQL:
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(serializer == null)
		{
			serializer = mapperConfig.getSerializer(klass);
			if(serializer == null)
			{
				serializer = mapperReflector.getSerializer(klass);
			}
		}
		return serializer;
	}
	
	public Deserializer<?> getDeserializer(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		return getDeserializer(mapperType, mapperReflector, mapperConfig, getFieldClass());
	}
	
	public Deserializer<?> getDeserializer(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig, Class<?> klass)
	{
		Deserializer<?> deserializer = null;
		try
		{
			switch(mapperType)
			{
				case JSON:
				{
					Class<?> deserializerClass = property.jsonDeserializer();
					if(!Void.class.equals(deserializerClass))
					{
						deserializer = (Deserializer<?>) deserializerClass.newInstance();
					}
					break;
				}
				case XML:
				{
					Class<?> deserializerClass = property.xmlDeserializer();
					if(!Void.class.equals(deserializerClass))
					{
						deserializer = (Deserializer<?>) deserializerClass.newInstance();
					}
					break;
				}
				case FORM:
				{
					Class<?> deserializerClass = property.formDeserializer();
					if(!Void.class.equals(deserializerClass))
					{
						deserializer = (Deserializer<?>) deserializerClass.newInstance();
					}
					break;
				}
				case TABLE:
				{
					Class<?> deserializerClass = property.tableDeserializer();
					if(!Void.class.equals(deserializerClass))
					{
						deserializer = (Deserializer<?>) deserializerClass.newInstance();
					}
					break;
				}
				case MYSQL:
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(deserializer == null)
		{
			deserializer = mapperConfig.getDeserializer(klass);
			if(deserializer == null)
			{
				deserializer = mapperReflector.getDeserializer(klass);
			}
		}
		return deserializer;
	}
	
	public String getName()
	{
		return AnnotationUtil.validate(property.name());
	}
	
	public String getTimeFormat()
	{
		return AnnotationUtil.validate(property.timeFormat());
	}
	
	public List<String> getExcludes()
	{
		return AnnotationUtil.validate(property.exclude());
	}
	
	public boolean isExcluded(String context)
	{
		boolean contains = false;
		String[] excludes = property.exclude();
		if(excludes != null)
		{
			for(String exclude : excludes)
			{
				if(exclude.equalsIgnoreCase(context))
				{
					contains = true;
					break;
				}
			}
		}
		return contains;
	}
	
	public boolean isDb()
	{
		return property.db();
	}
	
	public String getDbName()
	{
		return AnnotationUtil.validate(property.dbName());
	}
	
	public boolean isId()
	{
		return property.id();
	}
	
	public boolean isGenerated()
	{
		return property.generated();
	}
	
	public boolean isMysql()
	{
		return property.mysql();
	}
	
	public String getMysqlName()
	{
		return AnnotationUtil.validate(property.mysqlName());
	}
	
	public boolean isSerial()
	{
		return property.serial();
	}
	
	public String getSerialName()
	{
		return AnnotationUtil.validate(property.serialName());
	}
	
	public boolean isJson()
	{
		return property.json();
	}
	
	public String getJsonName()
	{
		return AnnotationUtil.validate(property.jsonName());
	}
	
	public boolean isXml()
	{
		return property.xml();
	}
	
	public String getXmlName()
	{
		return AnnotationUtil.validate(property.xmlName());
	}
	
	public boolean isAttribute()
	{
		return property.attribute();
	}
	
	public String getElementsName()
	{
		return AnnotationUtil.validate(property.elementsName());
	}
	
	public boolean isForm()
	{
		return property.form();
	}
	
	public String getFormName()
	{
		return AnnotationUtil.validate(property.formName());
	}
	
	public boolean isTable()
	{
		return property.table();
	}
	
	public String getTableName()
	{
		return AnnotationUtil.validate(property.tableName());
	}
	
	public boolean isRequired()
	{
		return property.required();
	}
	
	public List<String> getFilters()
	{
		return AnnotationUtil.validate(property.filter());
	}
	
	public List<String> getValidates()
	{
		return AnnotationUtil.validate(property.validate());
	}
	
}
