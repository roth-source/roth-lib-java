package roth.lib.map.inputter;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

import roth.lib.map.MapperConfig;
import roth.lib.map.MapperReflector;

public class MapperInputter<T> extends Inputter<T>
{
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected Type type;
	
	public MapperInputter()
	{
		
	}
	
	public MapperReflector getMapperReflector()
	{
		return mapperReflector;
	}
	
	public MapperConfig getMapperConfig()
	{
		return mapperConfig;
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
	
	public MapperInputter<T> setType(Type type)
	{
		this.type = type;
		return this;
	}
	
	@Override
	public T input(BufferedReader reader) throws IOException
	{
		return getMapperReflector().getMapper(getMapperConfig()).deserialize(reader, getType());
	}
	
}
