package roth.lib.java.reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import roth.lib.java.annotation.Property;
import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.deserializer.VoidDeserializer;
import roth.lib.java.filter.Filterer;
import roth.lib.java.lang.List;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.serializer.VoidSerializer;
import roth.lib.java.util.AnnotationUtil;
import roth.lib.java.util.ReflectionUtil;
import roth.lib.java.validate.Validator;

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
	
	public boolean isAttribute()
	{
		return property.attribute();
	}
	
	public String getElementsName()
	{
		return AnnotationUtil.validate(property.elementsName());
	}
	
	public String getTimeFormat(MapperType mapperType)
	{
		String timeFormat = null;
		switch(mapperType)
		{
			case JSON:
			{
				timeFormat = getJsonTimeFormat();
				break;
			}
			case XML:
			{
				timeFormat = getXmlTimeFormat();
				break;
			}
			case FORM:
			{
				timeFormat = getFormTimeFormat();
				break;
			}
			case TABLE:
			{
				timeFormat = getTableTimeFormat();
				break;
			}
			case MYSQL:
			{
				break;
			}
		}
		if(timeFormat == null)
		{
			switch(mapperType)
			{
				case JSON:
				case XML:
				case FORM:
				case TABLE:
				{
					timeFormat = getTimeFormat();
					break;
				}
				case MYSQL:
				{
					break;
				}
			}
		}
		return timeFormat;
	}
	
	public String filter(String value, MapperType mapperType)
	{
		if(value != null)
		{
			for(Filterer filterer : getFilterers(mapperType))
			{
				if(value != null)
				{
					value = filterer.filter(value);
				}
			}
		}
		return value;
	}
	
	public List<Filterer> getFilterers(MapperType mapperType)
	{
		List<Filterer> filterers = new List<Filterer>();
		switch(mapperType)
		{
			case JSON:
			{
				if(hasJsonFilter())
				{
					filterers = getFilterers(getJsonFilter());
				}
				break;
			}
			case XML:
			{
				if(hasXmlFilter())
				{
					filterers = getFilterers(getXmlFilter());
				}
				break;
			}
			case FORM:
			{
				if(hasFormFilter())
				{
					filterers = getFilterers(getFormFilter());
				}
				break;
			}
			case TABLE:
			{
				if(hasTableFilter())
				{
					filterers = getFilterers(getTableFilter());
				}
				break;
			}
			case MYSQL:
			{
				break;
			}
		}
		if(filterers == null)
		{
			switch(mapperType)
			{
				case JSON:
				case XML:
				case FORM:
				case TABLE:
				{
					filterers = getFilterers(getFilter());
					break;
				}
				case MYSQL:
				{
					break;
				}
			}
		}
		return filterers;
	}
	
	protected List<Filterer> getFilterers(List<Class<? extends Filterer>> filtererClasses)
	{
		List<Filterer> filterers = new List<Filterer>();
		for(Class<? extends Filterer> filtererClass : filtererClasses)
		{
			try
			{
				filterers.add(filtererClass.newInstance());
			}
			catch(InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return filterers;
	}
	
	public Serializer<?> getSerializer(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		return getSerializer(mapperType, mapperReflector, mapperConfig, getFieldClass());
	}
	
	public Serializer<?> getSerializer(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig, Class<?> klass)
	{
		Serializer<?> serializer = null;
		switch(mapperType)
		{
			case JSON:
			{
				serializer = getJsonSerializer();
				break;
			}
			case XML:
			{
				serializer = getXmlSerializer();
				break;
			}
			case FORM:
			{
				serializer = getFormSerializer();
				break;
			}
			case TABLE:
			{
				serializer = getTableSerializer();
				break;
			}
			case MYSQL:
			{
				break;
			}
		}
		if(serializer == null)
		{
			switch(mapperType)
			{
				case JSON:
				case XML:
				case FORM:
				case TABLE:
				{
					serializer = getSerializer();
					break;
				}
				case MYSQL:
				{
					break;
				}
			}
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
		switch(mapperType)
		{
			case JSON:
			{
				deserializer = getJsonDeserializer();
				break;
			}
			case XML:
			{
				deserializer = getXmlDeserializer();
				break;
			}
			case FORM:
			{
				deserializer = getFormDeserializer();
				break;
			}
			case TABLE:
			{
				deserializer = getTableDeserializer();
				break;
			}
			case MYSQL:
			{
				break;
			}
		}
		if(deserializer == null)
		{
			switch(mapperType)
			{
				case JSON:
				case XML:
				case FORM:
				case TABLE:
				{
					deserializer = getDeserializer();
					break;
				}
				case MYSQL:
				{
					break;
				}
			}
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
	
	protected String getName()
	{
		return AnnotationUtil.validate(property.name());
	}
	
	protected String getTimeFormat()
	{
		return AnnotationUtil.validate(property.timeFormat());
	}
	
	protected List<String> getExcludes()
	{
		return new List<String>(property.exclude());
	}
	
	protected boolean isDb()
	{
		return property.db();
	}
	
	protected String getDbName()
	{
		return AnnotationUtil.validate(property.dbName());
	}
	
	protected boolean isId()
	{
		return property.id();
	}
	
	protected boolean isGenerated()
	{
		return property.generated();
	}
	
	protected boolean isMysql()
	{
		return property.mysql();
	}
	
	protected String getMysqlName()
	{
		return AnnotationUtil.validate(property.mysqlName());
	}
	
	protected boolean isSerial()
	{
		return property.serial();
	}
	
	protected String getSerialName()
	{
		return AnnotationUtil.validate(property.serialName());
	}
	
	protected boolean hasFilter()
	{
		return property.filter().length > 0;
	}
	
	protected List<Class<? extends Filterer>> getFilter()
	{
		return new List<Class<? extends Filterer>>(property.filter());
	}
	
	protected boolean hasDeserializer()
	{
		return !VoidDeserializer.class.equals(property.deserializer());
	}
	
	protected Deserializer<?> getDeserializer()
	{
		try
		{
			return hasDeserializer() ? property.deserializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean hasSerializer()
	{
		return !VoidSerializer.class.equals(property.serializer());
	}
	
	protected Serializer<?> getSerializer()
	{
		try
		{
			return hasSerializer() ? property.serializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean isJson()
	{
		return property.json();
	}
	
	protected String getJsonName()
	{
		return AnnotationUtil.validate(property.jsonName());
	}
	
	protected String getJsonTimeFormat()
	{
		return AnnotationUtil.validate(property.jsonTimeFormat());
	}
	
	protected boolean hasJsonFilter()
	{
		return property.jsonFilter().length > 0;
	}
	
	protected List<Class<? extends Filterer>> getJsonFilter()
	{
		return new List<Class<? extends Filterer>>(property.jsonFilter());
	}
	
	protected boolean hasJsonDeserializer()
	{
		return !VoidDeserializer.class.equals(property.jsonDeserializer());
	}
	
	protected Deserializer<?> getJsonDeserializer()
	{
		try
		{
			return hasJsonDeserializer() ? property.jsonDeserializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean hasJsonSerializer()
	{
		return !VoidSerializer.class.equals(property.jsonSerializer());
	}
	
	protected Serializer<?> getJsonSerializer()
	{
		try
		{
			return hasJsonSerializer() ? property.jsonSerializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean isXml()
	{
		return property.xml();
	}
	
	protected String getXmlName()
	{
		return AnnotationUtil.validate(property.xmlName());
	}
	
	protected String getXmlTimeFormat()
	{
		return AnnotationUtil.validate(property.xmlTimeFormat());
	}
	
	protected boolean hasXmlFilter()
	{
		return property.xmlFilter().length > 0;
	}
	
	protected List<Class<? extends Filterer>> getXmlFilter()
	{
		return new List<Class<? extends Filterer>>(property.xmlFilter());
	}
	
	protected boolean hasXmlDeserializer()
	{
		return !VoidDeserializer.class.equals(property.xmlDeserializer());
	}
	
	protected Deserializer<?> getXmlDeserializer()
	{
		try
		{
			return hasXmlDeserializer() ? property.xmlDeserializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean hasXmlSerializer()
	{
		return !VoidSerializer.class.equals(property.xmlSerializer());
	}
	
	protected Serializer<?> getXmlSerializer()
	{
		try
		{
			return hasXmlSerializer() ? property.xmlSerializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean isForm()
	{
		return property.form();
	}
	
	protected String getFormName()
	{
		return AnnotationUtil.validate(property.formName());
	}
	
	protected String getFormTimeFormat()
	{
		return AnnotationUtil.validate(property.formTimeFormat());
	}
	
	protected boolean hasFormFilter()
	{
		return property.formFilter().length > 0;
	}
	
	protected List<Class<? extends Filterer>> getFormFilter()
	{
		return new List<Class<? extends Filterer>>(property.formFilter());
	}
	
	protected boolean hasFormDeserializer()
	{
		return !VoidDeserializer.class.equals(property.formDeserializer());
	}
	
	protected Deserializer<?> getFormDeserializer()
	{
		try
		{
			return hasFormDeserializer() ? property.formDeserializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean hasFormSerializer()
	{
		return !VoidSerializer.class.equals(property.formSerializer());
	}
	
	protected Serializer<?> getFormSerializer()
	{
		try
		{
			return hasFormSerializer() ? property.formSerializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean isTable()
	{
		return property.table();
	}
	
	protected String getTableName()
	{
		return AnnotationUtil.validate(property.tableName());
	}
	
	protected String getTableTimeFormat()
	{
		return AnnotationUtil.validate(property.tableTimeFormat());
	}
	
	protected boolean hasTableFilter()
	{
		return property.tableFilter().length > 0;
	}
	
	protected List<Class<? extends Filterer>> getTableFilter()
	{
		return new List<Class<? extends Filterer>>(property.tableFilter());
	}
	
	protected boolean hasTableDeserializer()
	{
		return !VoidDeserializer.class.equals(property.tableDeserializer());
	}
	
	protected Deserializer<?> getTableDeserializer()
	{
		try
		{
			return hasTableDeserializer() ? property.tableDeserializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	protected boolean hasTableSerializer()
	{
		return !VoidSerializer.class.equals(property.tableSerializer());
	}
	
	protected Serializer<?> getTableSerializer()
	{
		try
		{
			return hasTableSerializer() ? property.tableSerializer().newInstance() : null;
		}
		catch(InstantiationException | IllegalAccessException e)
		{
			return null;
		}
	}
	
	public boolean isRequired()
	{
		return property.required();
	}
	
	public boolean hasTrimLength()
	{
		return property.trimLength() > 0;
	}
	
	public boolean hasMaxLength()
	{
		return property.maxLength() > 0;
	}
	
	protected List<Class<? extends Validator>> getValidate()
	{
		return new List<Class<? extends Validator>>(property.validate());
	}
	
	public List<Validator> getValidators()
	{
		List<Validator> validators = new List<Validator>();
		for(Class<? extends Validator> validateorClass : getValidate())
		{
			try
			{
				validators.add(validateorClass.newInstance());
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return validators;
	}
	
}
