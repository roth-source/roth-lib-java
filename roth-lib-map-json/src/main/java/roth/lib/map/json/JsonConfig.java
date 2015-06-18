package roth.lib.map.json;

import roth.lib.map.Config;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;

public class JsonConfig extends Config
{
	protected static JsonConfig instance;
	
	protected boolean serializeEmptyArray = true;
	protected boolean serializeEmptyMap = true;
	
	public JsonConfig()
	{
		super();
	}
	
	public static JsonConfig get()
	{
		if(instance == null)
		{
			instance = new JsonConfig();
		}
		return instance;
	}
	
	public static void set(JsonConfig newInstance)
	{
		instance = newInstance;
	}
	
	public boolean isSerializeEmptyArray()
	{
		return serializeEmptyArray;
	}
	
	public boolean isSerializeEmptyMap()
	{
		return serializeEmptyMap;
	}
	
	public JsonConfig setSerializeEmptyArray(boolean serializeEmptyArray)
	{
		this.serializeEmptyArray = serializeEmptyArray;
		return this;
	}
	
	public JsonConfig setSerializeEmptyMap(boolean serializeEmptyMap)
	{
		this.serializeEmptyMap = serializeEmptyMap;
		return this;
	}
	
	@Override
	public JsonConfig setPrettyPrinting(boolean prettyPrinting)
	{
		this.prettyPrinting = prettyPrinting;
		return this;
	}
	
	@Override
	public JsonConfig setSerializeNulls(boolean serializeNulls)
	{
		this.serializeNulls = serializeNulls;
		return this;
	}
	
	@Override
	public JsonConfig setTimeFormat(String timeFormat)
	{
		super.setTimeFormat(timeFormat);
		return this;
	}
	
	@Override
	public <T> JsonConfig setSerializer(Class<T> klass, Serializer<T> serializer)
	{
		serializerMap.put(klass, serializer);
		return this;
	}
	
	@Override
	public <T> JsonConfig setDeserializer(Class<T> klass, Deserializer<T> deserializer)
	{
		deserializerMap.put(klass,  deserializer);
		return this;
	}
	
}
