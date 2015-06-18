package roth.lib.map.json;

import roth.lib.map.serializer.MapperSerializer;

public class JsonSerializer<T> extends MapperSerializer<T, JsonMapper, JsonConfig>
{
	
	public JsonSerializer()
	{
		
	}
	
	public JsonSerializer(T value)
	{
		setValue(value);
	}
	
	@Override
	public JsonSerializer<T> setValue(T value)
	{
		super.setValue(value);
		return this;
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
	public JsonSerializer<T> setMapper(JsonMapper mapper)
	{
		this.mapper = mapper;
		return this;
	}
	
	@Override
	public JsonSerializer<T> setConfig(JsonConfig config)
	{
		this.config = config;
		return this;
	}
	
}
