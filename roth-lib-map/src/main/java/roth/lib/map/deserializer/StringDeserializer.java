package roth.lib.map.deserializer;

import java.io.BufferedReader;
import java.io.IOException;

import roth.lib.map.Deserializer;


public class StringDeserializer extends Deserializer<String>
{
	
	public StringDeserializer()
	{
		super();
	}
	
	@Override
	public String deserialize(String value)
	{
		return value;
	}
	
	@Override
	public String deserialize(BufferedReader reader) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[1024];
		while(reader.read(buffer) > -1)
		{
			builder.append(buffer);
		}
		return builder.toString();
	}
	
}
