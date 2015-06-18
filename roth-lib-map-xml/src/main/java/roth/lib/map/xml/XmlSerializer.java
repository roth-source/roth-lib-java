package roth.lib.map.xml;

import roth.lib.map.serializer.MapperSerializer;

public class XmlSerializer<T> extends MapperSerializer<T, XmlMapper, XmlConfig>
{
	
	public XmlSerializer()
	{
		
	}
	
	public XmlSerializer(T value)
	{
		setValue(value);
	}
	
	@Override
	public XmlSerializer<T> setValue(T value)
	{
		super.setValue(value);
		return this;
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
	public XmlSerializer<T> setMapper(XmlMapper mapper)
	{
		this.mapper = mapper;
		return this;
	}
	
	@Override
	public XmlSerializer<T> setConfig(XmlConfig config)
	{
		this.config = config;
		return this;
	}
	
}
