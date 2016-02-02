package roth.lib.java.reflector;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map.Entry;

import roth.lib.java.DataUrl;
import roth.lib.java.annotation.Entity;
import roth.lib.java.deserializer.BigDecimalDeserializer;
import roth.lib.java.deserializer.BigIntegerDeserializer;
import roth.lib.java.deserializer.BooleanDeserializer;
import roth.lib.java.deserializer.ByteDeserializer;
import roth.lib.java.deserializer.CalendarDeserializer;
import roth.lib.java.deserializer.CharacterDeserializer;
import roth.lib.java.deserializer.DataUrlDeserializer;
import roth.lib.java.deserializer.DateDeserializer;
import roth.lib.java.deserializer.DayDeserializer;
import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.deserializer.DoubleDeserializer;
import roth.lib.java.deserializer.EnumDeserializer;
import roth.lib.java.deserializer.FloatDeserializer;
import roth.lib.java.deserializer.HourDeserializer;
import roth.lib.java.deserializer.IntegerDeserializer;
import roth.lib.java.deserializer.LongDeserializer;
import roth.lib.java.deserializer.MillisecondDeserializer;
import roth.lib.java.deserializer.MinuteDeserializer;
import roth.lib.java.deserializer.MonthDeserializer;
import roth.lib.java.deserializer.SecondDeserializer;
import roth.lib.java.deserializer.ShortDeserializer;
import roth.lib.java.deserializer.SqlDateDeserializer;
import roth.lib.java.deserializer.SqlTimeDeserializer;
import roth.lib.java.deserializer.SqlTimestampDeserializer;
import roth.lib.java.deserializer.StringDeserializer;
import roth.lib.java.deserializer.TimeDeserializer;
import roth.lib.java.deserializer.YearDeserializer;
import roth.lib.java.form.FormMapper;
import roth.lib.java.json.JsonMapper;
import roth.lib.java.lang.Map;
import roth.lib.java.lang.Set;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.serializer.CalendarSerializer;
import roth.lib.java.serializer.DateSerializer;
import roth.lib.java.serializer.EscapedSerializer;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.serializer.TimeSerializer;
import roth.lib.java.serializer.UnescapedSerializer;
import roth.lib.java.time.Day;
import roth.lib.java.time.Hour;
import roth.lib.java.time.Millisecond;
import roth.lib.java.time.Minute;
import roth.lib.java.time.Month;
import roth.lib.java.time.Second;
import roth.lib.java.time.Time;
import roth.lib.java.time.Year;
import roth.lib.java.util.ReflectionUtil;
import roth.lib.java.xml.XmlMapper;

public class MapperReflector
{
	protected static MapperReflector instance;
	
	protected Map<Class<?>, Serializer<?>> serializerMap = new Map<Class<?>, Serializer<?>>();
	protected Map<Class<?>, Deserializer<?>> deserializerMap = new Map<Class<?>, Deserializer<?>>();
	protected Set<String> nonEntityTypes = new Set<String>();
	
	public MapperReflector()
	{
		initSerializers();
		initDeserializers();
	}
	
	protected void initSerializers()
	{
		UnescapedSerializer unescapedSerializer = new UnescapedSerializer();
		EscapedSerializer<?> escapedSerializer = new EscapedSerializer<Object>();
		serializerMap.put(Enum.class, escapedSerializer);
		serializerMap.put(String.class, escapedSerializer);
		serializerMap.put(boolean.class, unescapedSerializer);
		serializerMap.put(byte.class, unescapedSerializer);
		serializerMap.put(short.class, unescapedSerializer);
		serializerMap.put(int.class, unescapedSerializer);
		serializerMap.put(long.class, unescapedSerializer);
		serializerMap.put(float.class, unescapedSerializer);
		serializerMap.put(double.class, unescapedSerializer);
		serializerMap.put(char.class, escapedSerializer);
		serializerMap.put(Boolean.class, unescapedSerializer);
		serializerMap.put(Number.class, unescapedSerializer);
		serializerMap.put(Character.class, escapedSerializer);
		serializerMap.put(DataUrl.class, escapedSerializer);
		serializerMap.put(Time.class, new TimeSerializer());
		serializerMap.put(Date.class, new DateSerializer());
		serializerMap.put(Calendar.class, new CalendarSerializer());
	}
	
	protected void initDeserializers()
	{
		deserializerMap.put(Enum.class, new EnumDeserializer());
		deserializerMap.put(String.class, new StringDeserializer());
		deserializerMap.put(boolean.class, new BooleanDeserializer(false));
		deserializerMap.put(Boolean.class,  new BooleanDeserializer(true));
		deserializerMap.put(byte.class, new ByteDeserializer(false));
		deserializerMap.put(Byte.class, new ByteDeserializer(true));
		deserializerMap.put(short.class, new ShortDeserializer(false));
		deserializerMap.put(Short.class, new ShortDeserializer(true));
		deserializerMap.put(int.class, new IntegerDeserializer(false));
		deserializerMap.put(Integer.class, new IntegerDeserializer(true));
		deserializerMap.put(long.class, new LongDeserializer(false));
		deserializerMap.put(Long.class, new LongDeserializer(true));
		deserializerMap.put(float.class, new FloatDeserializer(false));
		deserializerMap.put(Float.class, new FloatDeserializer(true));
		deserializerMap.put(double.class, new DoubleDeserializer(false));
		deserializerMap.put(Double.class, new DoubleDeserializer(true));
		deserializerMap.put(BigDecimal.class, new BigDecimalDeserializer());
		deserializerMap.put(BigInteger.class, new BigIntegerDeserializer());
		deserializerMap.put(char.class, new CharacterDeserializer(false));
		deserializerMap.put(Character.class, new CharacterDeserializer(true));
		deserializerMap.put(Year.class, new YearDeserializer());
		deserializerMap.put(Month.class, new MonthDeserializer());
		deserializerMap.put(Day.class, new DayDeserializer());
		deserializerMap.put(Hour.class, new HourDeserializer());
		deserializerMap.put(Minute.class, new MinuteDeserializer());
		deserializerMap.put(Second.class, new SecondDeserializer());
		deserializerMap.put(Millisecond.class, new MillisecondDeserializer());
		deserializerMap.put(Time.class, new TimeDeserializer());
		deserializerMap.put(java.sql.Date.class, new SqlDateDeserializer());
		deserializerMap.put(java.sql.Time.class, new SqlTimeDeserializer());
		deserializerMap.put(java.sql.Timestamp.class, new SqlTimestampDeserializer());
		deserializerMap.put(Date.class, new DateDeserializer());
		deserializerMap.put(GregorianCalendar.class, new CalendarDeserializer());
		deserializerMap.put(Calendar.class, new CalendarDeserializer());
		deserializerMap.put(DataUrl.class, new DataUrlDeserializer());
	}
	
	public static MapperReflector get()
	{
		if(instance == null)
		{
			instance = new MapperReflector();
		}
		return instance;
	}
	
	public boolean isEntity(Type type)
	{
		boolean entity = false;
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		if(!nonEntityTypes.contains(klass.getName()))
		{
			entity = getEntityAnnotation(type) != null;
		}
		return entity;
	}
	
	public Entity getEntityAnnotation(Type type)
	{
		Entity entity = null;
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		if(!klass.getName().startsWith("java."))
		{
			entity = klass.getDeclaredAnnotation(Entity.class);
			if(entity == null)
			{
				Class<?> superClass = klass.getSuperclass();
				if(superClass != null)
				{
					entity = getEntityAnnotation(superClass);
				}
			}
		}
		else
		{
			nonEntityTypes.add(klass.getName());
		}
		return entity;
	}
	
	public EntityReflector getEntityReflector(Type type)
	{
		EntityReflector entityReflector = null;
		Class<?> klass = ReflectionUtil.getTypeClass(type);
		if(!nonEntityTypes.contains(type))
		{
			Entity entity = getEntityAnnotation(type);
			if(entity != null)
			{
				entityReflector = new EntityReflector(type, entity);
			}
			else
			{
				nonEntityTypes.add(klass.getName());
			}
		}
		return entityReflector;
	}
	
	public Mapper getMapper(MapperType type)
	{
		return getMapper(type, MapperConfig.get());
	}
	
	public Mapper getMapper(MapperType type, MapperConfig mapperConfig)
	{
		Mapper mapper = null;
		switch(type)
		{
			case JSON:
			{
				mapper = getJsonMapper(mapperConfig);
				break;
			}
			case XML:
			{
				mapper = getXmlMapper(mapperConfig);
				break;
			}
			case FORM:
			{
				mapper = getFormMapper(mapperConfig);
				break;
			}
			case TABLE:
			{
				//mapper = getTableMapper(mapperConfig);
				break;
			}
			default:
			{
				mapper = getJsonMapper(mapperConfig);
				break;
			}
		}
		return mapper;
	}
	
	public JsonMapper getJsonMapper()
	{
		return getJsonMapper(MapperConfig.get());
	}
	
	public JsonMapper getJsonMapper(MapperConfig mapperConfig)
	{
		return new JsonMapper(mapperConfig);
	}

	public XmlMapper getXmlMapper()
	{
		return getXmlMapper(MapperConfig.get());
	}
	
	public XmlMapper getXmlMapper(MapperConfig mapperConfig)
	{
		return new XmlMapper(mapperConfig);
	}

	public FormMapper getFormMapper()
	{
		return getFormMapper(MapperConfig.get());
	}
	
	public FormMapper getFormMapper(MapperConfig mapperConfig)
	{
		return new FormMapper(mapperConfig);
	}
	
	/*
	public TableMapper getTableMapper()
	{
		return getTableMapper(MapperConfig.get());
	}
	
	public TableMapper getTableMapper(MapperConfig mapperConfig)
	{
		return new TableMapper(mapperConfig);
	}
	*/
	
	public Serializer<?> getSerializer(Class<?> klass)
	{
		Serializer<?> serializer = serializerMap.get(klass);
		if(serializer == null)
		{
			for(Entry<Class<?>, Serializer<?>> serializerEntry : serializerMap.entrySet())
			{
				if(serializerEntry.getKey().isAssignableFrom(klass))
				{
					serializer = serializerEntry.getValue();
					break;
				}
			}
		}
		return serializer;
	}
	
	public boolean isSerializable(Class<?> klass)
	{
		return getSerializer(klass) != null;
	}
	
	public Deserializer<?> getDeserializer(Class<?> klass)
	{
		Deserializer<?> deserializer = deserializerMap.get(klass);
		if(deserializer == null)
		{
			if(Enum.class.isAssignableFrom(klass))
			{
				deserializer = deserializerMap.get(Enum.class);
			}
		}
		return deserializer;
	}
	
	public <T> MapperReflector putSerializer(Class<T> klass, Serializer<T> serializer)
	{
		serializerMap.put(klass, serializer);
		return this;
	}
	
	public <T> MapperReflector putDeserializer(Class<T> klass, Deserializer<T> deserializer)
	{
		deserializerMap.put(klass, deserializer);
		return this;
	}
	
}
