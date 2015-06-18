package roth.lib.map;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import roth.lib.Characters;

public abstract class SerialMapper<C extends Config> extends Mapper implements Characters
{
	
	protected SerialMapper()
	{
		super();
	}
	
	public abstract C defaultConfig();
	
	public String serialize(Object object)
	{
		return serialize(object, defaultConfig());
	}
	
	public String serialize(Object object, C config)
	{
		StringWriter writer = new StringWriter();
		serialize(object, writer, config);
		return writer.toString();
	}
	
	public void serialize(Object object, OutputStream output)
	{
		serialize(object, output, defaultConfig());
	}
	
	public void serialize(Object object, OutputStream output, C config)
	{
		serialize(object, new OutputStreamWriter(output, UTF_8), config);
	}
	
	public void serialize(Object object, Writer writer)
	{
		serialize(object, writer, defaultConfig());
	}
	
	public abstract void serialize(Object object, Writer writer, C config);
	
	public String serialize(Map<String, ?> map)
	{
		return serialize(map, defaultConfig());
	}
	
	public String serialize(Map<String, ?> map, C config)
	{
		StringWriter writer = new StringWriter();
		serialize(map, writer, config);
		return writer.toString();
	}
	
	public void serialize(Map<String, ?> map, OutputStream output)
	{
		serialize(map, output, defaultConfig());
	}
	
	public void serialize(Map<String, ?> map, OutputStream output, C config)
	{
		serialize(map, new OutputStreamWriter(output, UTF_8), config);
	}
	
	public void serialize(Map<String, ?> map, Writer writer)
	{
		serialize(map, writer, defaultConfig());
	}
	
	public abstract void serialize(Map<String, ?> map, Writer writer, C config);
	
	public <T> T deserialize(String data, Class<T> klass)
	{
		return deserialize(data, klass, defaultConfig());
	}
	
	public <T> T deserialize(String data, Class<T> klass, C config)
	{
		return deserialize(new StringReader(data), klass, config);
	}
	
	public <T> T deserialize(InputStream input, Class<T> klass)
	{
		return deserialize(input, klass, defaultConfig());
	}
	
	public <T> T deserialize(InputStream input, Class<T> klass, C config)
	{
		return deserialize(new InputStreamReader(input, UTF_8), klass, config);
	}
	
	public <T> T deserialize(Reader reader, Class<T> klass)
	{
		return deserialize(reader, klass, defaultConfig());
	}
	
	public <T> T deserialize(String data, Generic<T> generic)
	{
		return deserialize(data, generic, defaultConfig());
	}
	
	public <T> T deserialize(String data, Generic<T> generic, C config)
	{
		return deserialize(new StringReader(data), generic, config);
	}
	
	public <T> T deserialize(InputStream input, Generic<T> generic)
	{
		return deserialize(input, generic, defaultConfig());
	}
	
	public <T> T deserialize(InputStream input, Generic<T> generic, C config)
	{
		return deserialize(new InputStreamReader(input, UTF_8), generic, config);
	}
	
	public <T> T deserialize(Reader reader, Generic<T> generic)
	{
		return deserialize(reader, generic, defaultConfig());
	}
	
	public <T> T deserialize(Reader reader, Generic<T> generic, C config)
	{
		return deserialize(reader, generic.getType(), config);
	}
	
	public <T> T deserialize(String data, Type type)
	{
		return deserialize(data, type, defaultConfig());
	}
	
	public <T> T deserialize(String data, Type type, C config)
	{
		return deserialize(new StringReader(data), type, config);
	}
	
	public <T> T deserialize(InputStream input, Type type)
	{
		return deserialize(input, type, defaultConfig());
	}
	
	public <T> T deserialize(InputStream input, Type type, C config)
	{
		return deserialize(new InputStreamReader(input, UTF_8), type, config);
	}
	
	public <T> T deserialize(Reader reader, Type type)
	{
		return deserialize(reader, type, defaultConfig());
	}
	
	public abstract <T> T deserialize(Reader reader, Type type, C config);
	
	public LinkedHashMap<String, Object> deserialize(String data)
	{
		return deserialize(data, defaultConfig());
	}
	
	public LinkedHashMap<String, Object> deserialize(String data, C config)
	{
		return deserialize(new StringReader(data), config);
	}
	
	public LinkedHashMap<String, Object> deserialize(InputStream input)
	{
		return deserialize(input, defaultConfig());
	}
	
	public LinkedHashMap<String, Object> deserialize(InputStream input, C config)
	{
		return deserialize(new InputStreamReader(input, UTF_8), config);
	}
	
	public LinkedHashMap<String, Object> deserialize(Reader reader)
	{
		return deserialize(reader, defaultConfig());
	}
	
	public abstract LinkedHashMap<String, Object> deserialize(Reader reader, C config);
	
	public String format(String data)
	{
		return format(new StringReader(data));
	}
	
	public String format(Reader reader)
	{
		return null;
	}
	
	protected String readUntil(Reader reader, Character...untils)
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			List<Character> untilList = Arrays.asList(untils);
			int b;
			char c;
			while((b = reader.read()) > -1)
			{
				c = (char) b;
				if(untilList.contains(c))
				{
					break;
				}
				builder.append(c);
			}
		}
		catch(Exception e)
		{
			
		}
		return builder.toString();
	}
	
	protected String readUntil(Reader reader, String until)
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			int b;
			char c;
			while((b = reader.read()) > -1)
			{
				c = (char) b;
				builder.append(c);
				if(builder.toString().endsWith(until))
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return builder.toString();
	}
	
}
