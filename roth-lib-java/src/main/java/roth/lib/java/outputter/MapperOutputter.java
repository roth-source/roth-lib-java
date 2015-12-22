package roth.lib.java.outputter;

import java.io.BufferedWriter;
import java.io.IOException;

import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.MapperReflector;

public class MapperOutputter<T> extends Outputter
{
	protected MapperType mapperType;
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected T value;
	
	public MapperOutputter(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig, T value)
	{
		this.mapperType = mapperType;
		this.mapperReflector = mapperReflector;
		this.mapperConfig = mapperConfig;
		this.value = value;
	}
	
	public MapperReflector getMapperReflector()
	{
		return mapperReflector;
	}
	
	public MapperConfig getMapperConfig()
	{
		if(mapperConfig == null)
		{
			mapperConfig = MapperConfig.get();
		}
		return mapperConfig;
	}
	
	public MapperType getMapperType()
	{
		return mapperType;
	}
	
	public MapperOutputter<T> setMapperReflector(MapperReflector mapperReflector)
	{
		this.mapperReflector = mapperReflector;
		return this;
	}
	
	public MapperOutputter<T> setMapperConfig(MapperConfig mapperConfig)
	{
		this.mapperConfig = mapperConfig;
		return this;
	}
	
	public MapperOutputter<T> setMapperType(MapperType mapperType)
	{
		this.mapperType = mapperType;
		return this;
	}
	
	public T getValue()
	{
		return value;
	}
	
	public MapperOutputter<T> setValue(T value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public void output(BufferedWriter writer) throws IOException
	{
		T value = getValue();
		if(value != null)
		{
			getMapperReflector().getMapper(getMapperType(), getMapperConfig()).serialize(value, writer);
		}
		writer.flush();
	}
	
}
