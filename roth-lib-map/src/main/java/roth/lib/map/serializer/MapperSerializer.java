package roth.lib.map.serializer;

import java.io.BufferedWriter;
import java.io.IOException;

import roth.lib.map.Config;
import roth.lib.map.SerialMapper;
import roth.lib.map.Serializer;

public abstract class MapperSerializer<T, M extends SerialMapper<C>, C extends Config> extends Serializer<T>
{
	protected M mapper;
	protected C config;
	
	protected MapperSerializer()
	{
		
	}
	
	public abstract M getMapper();
	public abstract C getConfig();
	public abstract MapperSerializer<T, M, C> setMapper(M mapper);
	public abstract MapperSerializer<T, M, C> setConfig(C config);
	
	@Override
	public void serialize(BufferedWriter writer) throws IOException
	{
		if(value != null)
		{
			getMapper().serialize(value, writer, getConfig());
		}
		writer.flush();
	}
	
}
