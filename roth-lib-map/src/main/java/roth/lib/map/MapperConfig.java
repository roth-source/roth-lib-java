package roth.lib.map;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import roth.lib.map.deserializer.BigDecimalDeserializer;
import roth.lib.map.deserializer.BigIntegerDeserializer;
import roth.lib.map.deserializer.BooleanDeserializer;
import roth.lib.map.deserializer.ByteDeserializer;
import roth.lib.map.deserializer.CalendarDeserializer;
import roth.lib.map.deserializer.CharacterDeserializer;
import roth.lib.map.deserializer.DateDeserializer;
import roth.lib.map.deserializer.Deserializer;
import roth.lib.map.deserializer.DoubleDeserializer;
import roth.lib.map.deserializer.EnumDeserializer;
import roth.lib.map.deserializer.FloatDeserializer;
import roth.lib.map.deserializer.IntegerDeserializer;
import roth.lib.map.deserializer.LongDeserializer;
import roth.lib.map.deserializer.ShortDeserializer;
import roth.lib.map.deserializer.StringDeserializer;
import roth.lib.map.deserializer.TemporalDeserializer;
import roth.lib.map.deserializer.TimeDeserializer;
import roth.lib.map.deserializer.TimestampDeserializer;
import roth.lib.map.serializer.CalendarSerializer;
import roth.lib.map.serializer.DateSerializer;
import roth.lib.map.serializer.EnumSerializer;
import roth.lib.map.serializer.EscapedSerializer;
import roth.lib.map.serializer.Serializer;
import roth.lib.map.serializer.TemporalSerializer;
import roth.lib.map.serializer.UnescapedSerializer;

public class MapperConfig
{
	protected static MapperConfig instance;
	
	protected boolean debug = false;
	protected boolean prettyPrinting = false;
	protected boolean serializeNulls = false;
	protected boolean serializeEmptyArray = true;
	protected boolean serializeEmptyMap = true;
	protected String timeFormat = null;
	protected LinkedHashMap<Class<?>, Serializer<?>> serializerMap = new LinkedHashMap<Class<?>, Serializer<?>>();
	protected LinkedHashMap<Class<?>, Deserializer<?>> deserializerMap = new LinkedHashMap<Class<?>, Deserializer<?>>();
	
	public MapperConfig()
	{
		this(false);
	}
	
	public MapperConfig(boolean debug)
	{
		this.debug = debug;
		if(debug)
		{
			this.prettyPrinting = true;
		}
		initSerializers();
		initDeserializers();
	}
	
	protected void initSerializers()
	{
		UnescapedSerializer unescapedSerializer = new UnescapedSerializer();
		EscapedSerializer<?> escapedSerializer = new EscapedSerializer<Object>();
		DateSerializer dateSerializer = new DateSerializer();
		CalendarSerializer calendarSerializer = new CalendarSerializer();
		serializerMap.put(Enum.class, new EnumSerializer());
		serializerMap.put(boolean.class, unescapedSerializer);
		serializerMap.put(Boolean.class, unescapedSerializer);
		serializerMap.put(byte.class, unescapedSerializer);
		serializerMap.put(Byte.class, unescapedSerializer);
		serializerMap.put(short.class, unescapedSerializer);
		serializerMap.put(Short.class, unescapedSerializer);
		serializerMap.put(int.class, unescapedSerializer);
		serializerMap.put(Integer.class, unescapedSerializer);
		serializerMap.put(long.class, unescapedSerializer);
		serializerMap.put(Long.class, unescapedSerializer);
		serializerMap.put(float.class, unescapedSerializer);
		serializerMap.put(Float.class, unescapedSerializer);
		serializerMap.put(double.class, unescapedSerializer);
		serializerMap.put(Double.class, unescapedSerializer);
		serializerMap.put(BigDecimal.class, unescapedSerializer);
		serializerMap.put(BigInteger.class, unescapedSerializer);
		serializerMap.put(char.class, escapedSerializer);
		serializerMap.put(Character.class, escapedSerializer);
		serializerMap.put(String.class, escapedSerializer);
		serializerMap.put(Time.class, dateSerializer);
		serializerMap.put(Timestamp.class, dateSerializer);
		serializerMap.put(java.sql.Date.class, dateSerializer);
		serializerMap.put(Date.class, dateSerializer);
		serializerMap.put(GregorianCalendar.class, calendarSerializer);
		serializerMap.put(Calendar.class, calendarSerializer);
	}
	
	protected void initDeserializers()
	{
		deserializerMap.put(Enum.class, new EnumDeserializer());
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
		deserializerMap.put(String.class, new StringDeserializer());
		deserializerMap.put(Time.class, new TimeDeserializer());
		deserializerMap.put(Timestamp.class, new TimestampDeserializer());
		deserializerMap.put(java.sql.Date.class, new DateDeserializer());
		deserializerMap.put(Date.class, new DateDeserializer());
		deserializerMap.put(GregorianCalendar.class, new CalendarDeserializer());
		deserializerMap.put(Calendar.class, new CalendarDeserializer());
	}
	
	public static MapperConfig get()
	{
		if(instance == null)
		{
			instance = new MapperConfig();
		}
		return instance;
	}
	
	public static void set(MapperConfig mapperConfig)
	{
		instance = mapperConfig;
	}
	
	public boolean isDebug()
	{
		return debug;
	}
	
	public boolean isPrettyPrinting()
	{
		return prettyPrinting;
	}
	
	public boolean isSerializeNulls()
	{
		return serializeNulls;
	}
	
	public boolean isSerializeEmptyArray()
	{
		return serializeEmptyArray;
	}
	
	public boolean isSerializeEmptyMap()
	{
		return serializeEmptyMap;
	}
	
	public String getTimeFormat()
	{
		return timeFormat;
	}
	
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
			for(Entry<Class<?>, Deserializer<?>> deserializerEntry : deserializerMap.entrySet())
			{
				if(deserializerEntry.getKey().isAssignableFrom(klass))
				{
					deserializer = deserializerEntry.getValue();
					break;
				}
			}
		}
		return deserializer;
	}
	
	public MapperConfig setTimeFormat(String timeFormat)
	{
		this.timeFormat = timeFormat;
		for(Serializer<?> serializer : serializerMap.values())
		{
			if(serializer instanceof TemporalSerializer)
			{
				((TemporalSerializer<?>) serializer).setTimeFormat(timeFormat);
			}
		}
		for(Deserializer<?> deserializer : deserializerMap.values())
		{
			if(deserializer instanceof TemporalDeserializer)
			{
				((TemporalDeserializer<?>) deserializer).setTimeFormat(timeFormat);
			}
		}
		return this;
	}
	
	public MapperConfig setDebug(boolean debug)
	{
		this.debug = debug;
		return this;
	}
	
	public MapperConfig setPrettyPrinting(boolean prettyPrinting)
	{
		this.prettyPrinting = prettyPrinting;
		return this;
	}
	
	public MapperConfig setSerializeNulls(boolean serializeNulls)
	{
		this.serializeNulls = serializeNulls;
		return this;
	}
	
	public MapperConfig setSerializeEmptyArray(boolean serializeEmptyArray)
	{
		this.serializeEmptyArray = serializeEmptyArray;
		return this;
	}
	
	public MapperConfig setSerializeEmptyMap(boolean serializeEmptyMap)
	{
		this.serializeEmptyMap = serializeEmptyMap;
		return this;
	}
	
	public <T> MapperConfig putSerializer(Class<T> klass, Serializer<T> serializer)
	{
		serializerMap.put(klass, serializer);
		return this;
	}
	
	public <T> MapperConfig putDeserializer(Class<T> klass, Deserializer<T> deserializer)
	{
		deserializerMap.put(klass, deserializer);
		return this;
	}
	
}
