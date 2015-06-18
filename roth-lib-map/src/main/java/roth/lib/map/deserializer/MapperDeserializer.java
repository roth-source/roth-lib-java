package roth.lib.map.deserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

import roth.lib.map.Config;
import roth.lib.map.Deserializer;
import roth.lib.map.SerialMapper;

public abstract class MapperDeserializer<T, M extends SerialMapper<C>, C extends Config> extends Deserializer<T>
{
	protected M mapper;
	protected C config;
	protected Type type;
	
	protected MapperDeserializer()
	{
		
	}
	
	public Type getType()
	{
		return type;
	}
	
	public abstract M getMapper();
	public abstract C getConfig();
	public abstract MapperDeserializer<T, M, C> setMapper(M mapper);
	public abstract MapperDeserializer<T, M, C> setConfig(C config);
	
	@Override
	public T deserialize(BufferedReader reader) throws IOException
	{
		return getMapper().deserialize(reader, type, getConfig());
	}
	
}
