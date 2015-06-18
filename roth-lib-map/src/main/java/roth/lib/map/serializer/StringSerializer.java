package roth.lib.map.serializer;

import java.io.BufferedWriter;
import java.io.IOException;

import roth.lib.map.Serializer;

public class StringSerializer extends Serializer<String>
{
	public StringSerializer()
	{
		
	}
	
	public StringSerializer(String value)
	{
		this.value = value;
	}
	
	@Override
	public String serialize(String value)
	{
		return value;
	}
	
	@Override
	public StringSerializer setValue(String value)
	{
		this.value = value;
		return this;
	}
	
	@Override
	public void serialize(BufferedWriter writer) throws IOException
	{
		if(value != null)
		{
			writer.write(value);
		}
		writer.flush();
	}
	
}
