package roth.lib.java.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;

import roth.lib.java.Callback;
import roth.lib.java.Characters;
import roth.lib.java.Generic;
import roth.lib.java.Model;
import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertyReflector;
import roth.lib.java.serializer.Serializer;

public abstract class Mapper implements Characters
{
	protected boolean prettyPrint = false;
	protected MapperType mapperType;
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected String context;
	protected List<Callback<?>> callbacks = new List<Callback<?>>();
	protected int tabs;
	
	protected Mapper(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		this.mapperType = mapperType;
		this.mapperReflector = mapperReflector != null ? mapperReflector : MapperReflector.get();
		this.mapperConfig = mapperConfig != null ? mapperConfig : MapperConfig.get();
	}
	
	public boolean isPrettyPrint()
	{
		return prettyPrint;
	}
	
	public MapperType getMapperType()
	{
		return mapperType;
	}
	
	public MapperReflector getMapperReflector()
	{
		return mapperReflector;
	}
	
	public MapperConfig getMapperConfig()
	{
		return mapperConfig;
	}
	
	public boolean hasContext()
	{
		return context != null;
	}
	
	public String getContext()
	{
		return context;
	}
	
	public List<Callback<?>> getCallbacks()
	{
		return callbacks;
	}
	
	public int getTabs()
	{
		return tabs;
	}
	
	public String getTimeFormat(PropertyReflector propertyReflector)
	{
		return propertyReflector != null ? propertyReflector.getTimeFormat(getMapperType()) : null;
	}
	
	public Serializer<?> getSerializer(Class<?> klass)
	{
		Serializer<?> serializer = mapperConfig.getSerializer(klass);
		if(serializer == null)
		{
			serializer = mapperReflector.getSerializer(klass);
		}
		return serializer;
	}
	
	public Serializer<?> getSerializer(Class<?> klass, PropertyReflector propertyReflector)
	{
		Serializer<?> serializer = null;
		if(propertyReflector != null)
		{
			serializer = propertyReflector.getSerializer(getMapperType(), getMapperReflector(), getMapperConfig(), klass);
		}
		else
		{
			serializer = getMapperConfig().getSerializer(klass);
			if(serializer == null)
			{
				serializer = getMapperReflector().getSerializer(klass);
			}
		}
		return serializer;
	}
	
	public Deserializer<?> getDeserializer(Class<?> klass, PropertyReflector propertyReflector)
	{
		Deserializer<?> deserializer = null;
		if(propertyReflector != null)
		{
			deserializer = propertyReflector.getDeserializer(getMapperType(), getMapperReflector(), getMapperConfig(), klass);
		}
		else
		{
			deserializer = getMapperConfig().getDeserializer(klass);
			if(deserializer == null)
			{
				deserializer = getMapperReflector().getDeserializer(klass);
			}
		}
		return deserializer;
	}
	
	public Mapper setPrettyPrint(boolean prettyPrint)
	{
		this.prettyPrint = prettyPrint;
		return this;
	}
	
	public Mapper setMapperReflector(MapperReflector mapperReflector)
	{
		this.mapperReflector = mapperReflector;
		return this;
	}
	
	public Mapper setMapperConfig(MapperConfig mapperConfig)
	{
		this.mapperConfig = mapperConfig;
		return this;
	}
	
	public Mapper setContext(String context)
	{
		this.context = context;
		return this;
	}
	
	public Mapper setCallbacks(List<Callback<?>> callbacks)
	{
		this.callbacks = callbacks;
		return this;
	}
	
	public Mapper setCallbacks(Callback<?>... callbacks)
	{
		this.callbacks = new List<Callback<?>>(callbacks);
		return this;
	}
	
	public Mapper setTabs(int tabs)
	{
		this.tabs = tabs;
		return this;
	}
	
	public Mapper incrementTabs()
	{
		this.tabs += 1;
		return this;
	}
	
	public Mapper decrementTabs()
	{
		this.tabs -= 1;
		return this;
	}
	
	
	
	
	// SERIALIZE FROM OBJECT OVERLOADING
	
	public String serialize(Object value)
	{
		Writer writer = new StringWriter();
		serialize(value, writer);
		return writer.toString();
	}
	
	public void serialize(Object value, File file)
	{
		try(FileOutputStream output = new FileOutputStream(file))
		{
			serialize(value, output);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public void serialize(Object value, OutputStream output)
	{
		serialize(value, new OutputStreamWriter(output, UTF_8));
	}
	
	public abstract void serialize(Object value, Writer writer);
	
	
	
	
	// SERIALIZE FROM MAP OVERLOADING
	
	public String serialize(java.util.Map<String, ?> map)
	{
		Writer writer = new StringWriter();
		serialize(map, writer);
		return writer.toString();
	}
	
	public void serialize(java.util.Map<String, ?> map, OutputStream output)
	{
		serialize(map, new OutputStreamWriter(output, UTF_8));
	}
	
	public abstract void serialize(java.util.Map<String, ?> map, Writer writer);
	
	
	
	
	// DESERIALIZE TO GENERIC OVERLOADING
	
	public <T> T deserialize(String data, Generic<T> generic)
	{
		return deserialize(new StringReader(data), generic);
	}
	
	public <T> T deserialize(File file, Generic<T> generic)
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			return deserialize(input, generic);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public <T> T deserialize(InputStream input, Generic<T> generic)
	{
		return deserialize(new InputStreamReader(input, UTF_8), generic);
	}
	
	public <T> T deserialize(Reader reader, Generic<T> generic)
	{
		return deserialize(reader, generic.getType());
	}
	
	
	
	
	// DESERIALIZE TO CLASS OVERLOADING
	
	public <T> T deserialize(String data, Class<T> klass)
	{
		return deserialize(new StringReader(data), klass);
	}
	
	public <T> T deserialize(File file, Class<T> klass)
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			return deserialize(input, klass);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public <T> T deserialize(InputStream input, Class<T> klass)
	{
		return deserialize(new InputStreamReader(input, UTF_8), klass);
	}
	
	public <T> T deserialize(Reader reader, Class<T> klass)
	{
		return deserialize(reader, (Type) klass);
	}
	
	
	
	
	// DESERIALIZE TO TYPE OVERLOADING
	
	public <T> T deserialize(String data, Type type)
	{
		return deserialize(new StringReader(data), type);
	}
	
	public <T> T deserialize(File file, Type type)
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			return deserialize(input, type);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public <T> T deserialize(InputStream input, Type type)
	{
		return deserialize(new InputStreamReader(input, UTF_8), type);
	}
	
	public abstract <T> T deserialize(Reader reader, Type type);
	
	
	
	
	// DESERIALIZE TO MAP OVERLOADING
	
	public Map<String, Object> deserialize(String data)
	{
		return deserialize(new StringReader(data));
	}
	
	public Map<String, Object> deserialize(File file)
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			return deserialize(input);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public Map<String, Object> deserialize(InputStream input)
	{
		return deserialize(new InputStreamReader(input, UTF_8));
	}
	
	public abstract Map<String, Object> deserialize(Reader reader);
	
	
	
	
	// PRETTY PRINT OVERLOADING
	
	public String prettyPrint(String data)
	{
		return prettyPrint(new StringReader(data));
	}
	
	public String prettyPrint(InputStream input)
	{
		return prettyPrint(new InputStreamReader(input, UTF_8));
	}
	
	public abstract String prettyPrint(Reader reader);
	
	
	
	
	
	public static String readUntil(Reader reader, Character...untils)
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			List<Character> untilList = List.fromArray(untils);
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
	
	public static String readUntil(Reader reader, String until)
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
	
	public static String tabs(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < tabs; i++)
		{
			builder.append(TAB);
		}
		return builder.toString();
	}
	
	protected void setDeserializedName(Object model, String name)
	{
		if(model instanceof Model)
		{
			((Model) model).setDeserializedName(name);
		}
	}
	
}
