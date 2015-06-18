package roth.lib.map.xml;

import java.lang.reflect.Type;

import roth.lib.map.Generic;
import roth.lib.map.deserializer.MapperDeserializer;

public class XmlDeserializer<T> extends MapperDeserializer<T, XmlMapper, XmlConfig>
{
	
	public XmlDeserializer(Type type)
	{
		this.type = type;
	}
	
	public XmlDeserializer(Class<T> klass)
	{
		this.type = klass;
	}
	
	public XmlDeserializer(Generic<T> generic)
	{
		this.type = generic.getType();
	}
	
	@Override
	public XmlMapper getMapper()
	{
		if(mapper == null)
		{
			mapper = XmlMapper.get();
		}
		return mapper;
	}
	
	@Override
	public XmlConfig getConfig()
	{
		if(config == null)
		{
			config = XmlConfig.get();
		}
		return config;
	}
	
	@Override
	public XmlDeserializer<T> setMapper(XmlMapper mapper)
	{
		this.mapper = mapper;
		return this;
	}
	
	@Override
	public XmlDeserializer<T> setConfig(XmlConfig config)
	{
		this.config = config;
		return this;
	}
	
}
