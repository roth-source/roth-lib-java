package roth.lib.map;

import java.io.BufferedWriter;
import java.io.IOException;

import roth.lib.outputter.Outputter;

public class MapperOutputter<T> extends Outputter
{
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected T value;
	
	public MapperOutputter()
	{
		
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
			getMapperReflector().getMapper(getMapperConfig()).serialize(value, writer);
		}
		writer.flush();
	}
	
}
