package roth.lib.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import roth.lib.Characters;

public abstract class Deserializer<T>
{
	
	protected Deserializer()
	{
		
	}
	
	public T deserialize(String value, Class<?> klass)
	{
		return deserialize(value);
	}
	
	protected T deserialize(String value)
	{
		return null;
	}
	
	public T deserialize(InputStream input) throws IOException
	{
		return deserialize(new InputStreamReader(input, Characters.UTF_8));
	}
	
	public T deserialize(Reader reader) throws IOException
	{
		return deserialize(new BufferedReader(reader));
	}
	
	public T deserialize(BufferedReader reader) throws IOException
	{
		return null;
	}
	
}
