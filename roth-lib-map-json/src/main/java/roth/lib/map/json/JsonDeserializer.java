package roth.lib.map.json;

import java.lang.reflect.Type;

import roth.lib.map.Generic;
import roth.lib.map.deserializer.MapperDeserializer;

public class JsonDeserializer<T> extends MapperDeserializer<T, JsonMapper, JsonConfig>
{
	
	public JsonDeserializer(Type type)
	{
		this.type = type;
	}
	
	public JsonDeserializer(Class<T> klass)
	{
		this.type = klass;
	}
	
	public JsonDeserializer(Generic<T> generic)
	{
		this.type = generic.getType();
	}
	
	@Override
	public JsonMapper getMapper()
	{
		if(mapper == null)
		{
			mapper = JsonMapper.get();
		}
		return mapper;
	}
	
	@Override
	public JsonConfig getConfig()
	{
		if(config == null)
		{
			config = JsonConfig.get();
		}
		return config;
	}
	
	@Override
	public JsonDeserializer<T> setMapper(JsonMapper mapper)
	{
		this.mapper = mapper;
		return this;
	}
	
	@Override
	public JsonDeserializer<T> setConfig(JsonConfig config)
	{
		this.config = config;
		return this;
	}
	
}
