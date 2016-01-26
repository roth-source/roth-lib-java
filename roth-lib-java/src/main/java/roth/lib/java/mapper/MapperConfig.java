package roth.lib.java.mapper;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.serializer.Serializer;

public class MapperConfig
{
	protected static MapperConfig instance;
	
	protected boolean serializeNulls = false;
	protected boolean serializeEmptyArray = true;
	protected boolean serializeEmptyMap = true;
	protected LinkedHashMap<Class<?>, Serializer<?>> serializerMap = new LinkedHashMap<Class<?>, Serializer<?>>();
	protected LinkedHashMap<Class<?>, Deserializer<?>> deserializerMap = new LinkedHashMap<Class<?>, Deserializer<?>>();
	
	public MapperConfig()
	{
		
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
		return deserializerMap.get(klass);
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
