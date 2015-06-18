package roth.lib.map.form;

import roth.lib.map.serializer.MapperSerializer;

public class FormSerializer<T> extends MapperSerializer<T, FormMapper, FormConfig>
{
	
	public FormSerializer()
	{
		
	}
	
	public FormSerializer(T value)
	{
		setValue(value);
	}
	
	@Override
	public FormSerializer<T> setValue(T value)
	{
		super.setValue(value);
		return this;
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
	public FormSerializer<T> setMapper(FormMapper mapper)
	{
		this.mapper = mapper;
		return this;
	}
	
	@Override
	public FormSerializer<T> setConfig(FormConfig config)
	{
		this.config = config;
		return this;
	}
	
}
