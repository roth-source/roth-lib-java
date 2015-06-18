package roth.lib.map;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public abstract class Serializer<T>
{
	protected T value;
	
	protected Serializer()
	{
		
	}
	
	public T getValue()
	{
		return value;
	}
	
	public Serializer<T> setValue(T value)
	{
		this.value = value;
		return this;
	}
	
	public boolean isEscapable()
	{
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public String serializeInternal(Object object)
	{
		return serialize((T) object);
	}
	
	public String serialize(T object)
	{
		return null;
	}
	
	public void serialize(OutputStream output) throws IOException
	{
		serialize(new OutputStreamWriter(output, Mapper.UTF_8));
	}
	
	public void serialize(Writer writer) throws IOException
	{
		serialize(new BufferedWriter(writer));
	}
	
	public void serialize(BufferedWriter writer) throws IOException
	{
		
	}
	
}
