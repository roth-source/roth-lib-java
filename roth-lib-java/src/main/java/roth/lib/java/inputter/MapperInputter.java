package roth.lib.java.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.MapperReflector;

public class MapperInputter<T> extends Inputter<T>
{
	protected MapperType mapperType;
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected Type type;
	
	public MapperInputter(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig, Type type)
	{
		this.mapperType = mapperType;
		this.mapperReflector = mapperReflector;
		this.mapperConfig = mapperConfig;
		this.type = type;
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
	
	public Type getType()
	{
		return type;
	}
	
	public MapperInputter<T> setMapperReflector(MapperReflector mapperReflector)
	{
		this.mapperReflector = mapperReflector;
		return this;
	}
	
	public MapperInputter<T> setMapperConfig(MapperConfig mapperConfig)
	{
		this.mapperConfig = mapperConfig;
		return this;
	}
	
	public MapperInputter<T> setMapperType(MapperType mapperType)
	{
		this.mapperType = mapperType;
		return this;
	}
	
	public MapperInputter<T> setType(Type type)
	{
		this.type = type;
		return this;
	}
	
	@Override
	public T input(BufferedReader reader) throws IOException
	{
		return getMapperReflector().getMapper(getMapperType(), getMapperConfig()).deserialize(reader, getType());
	}
	
}
