package roth.lib.map.form;

import java.lang.reflect.Type;

import roth.lib.map.Generic;
import roth.lib.map.deserializer.MapperDeserializer;

public class FormDeserializer<T> extends MapperDeserializer<T, FormMapper, FormConfig>
{
	
	public FormDeserializer(Type type)
	{
		this.type = type;
	}
	
	public FormDeserializer(Class<T> klass)
	{
		this.type = klass;
	}
	
	public FormDeserializer(Generic<T> generic)
	{
		this.type = generic.getType();
	}
	
	@Override
	public FormMapper getMapper()
	{
		if(mapper == null)
		{
			mapper = FormMapper.get();
		}
		return mapper;
	}
	
	@Override
	public FormConfig getConfig()
	{
		if(config == null)
		{
			config = FormConfig.get();
		}
		return config;
	}
	
	@Override
	public FormDeserializer<T> setMapper(FormMapper mapper)
	{
		this.mapper = mapper;
		return this;
	}
	
	@Override
	public FormDeserializer<T> setConfig(FormConfig config)
	{
		this.config = config;
		return this;
	}
	
}
