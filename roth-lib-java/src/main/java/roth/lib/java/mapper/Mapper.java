package roth.lib.java.mapper;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import roth.lib.java.Callback;
import roth.lib.java.Characters;
import roth.lib.java.Generic;
import roth.lib.java.Model;
import roth.lib.java.reflector.MapperReflector;

public abstract class Mapper implements Characters
{
	protected MapperType mapperType;
	protected MapperReflector mapperReflector;
	protected MapperConfig mapperConfig;
	protected String context;
	protected LinkedList<Callback<?>> callbacks = new LinkedList<Callback<?>>();
	protected String timeFormat;
	protected int tabs;
	
	protected Mapper(MapperType mapperType, MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		this.mapperType = mapperType;
		this.mapperReflector = mapperReflector != null ? mapperReflector : MapperReflector.get();
		this.mapperConfig = mapperConfig != null ? mapperConfig : MapperConfig.get();
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
	
	public LinkedList<Callback<?>> getCallbacks()
	{
		return callbacks;
	}
	
	public String getTimeFormat()
	{
		return timeFormat != null ? timeFormat : mapperConfig.getTimeFormat();
	}
	
	public int getTabs()
	{
		return tabs;
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
	
	public Mapper setCallbacks(LinkedList<Callback<?>> callbacks)
	{
		this.callbacks = callbacks;
		return this;
	}
	
	public Mapper setCallbacks(Callback<?>... callbacks)
	{
		this.callbacks = new LinkedList<Callback<?>>(Arrays.asList(callbacks));
		return this;
	}
	
	public Mapper setTimeFormat(String timeFormat)
	{
		this.timeFormat = timeFormat;
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
	
	public void serialize(Object value, OutputStream output)
	{
		serialize(value, new OutputStreamWriter(output, UTF_8));
	}
	
	public abstract void serialize(Object value, Writer writer);
	
	
	
	
	// SERIALIZE FROM MAP OVERLOADING
	
	public String serialize(Map<String, ?> map)
	{
		Writer writer = new StringWriter();
		serialize(map, writer);
		return writer.toString();
	}
	
	public void serialize(Map<String, ?> map, OutputStream output)
	{
		serialize(map, new OutputStreamWriter(output, UTF_8));
	}
	
	public abstract void serialize(Map<String, ?> map, Writer writer);
	
	
	
	
	// DESERIALIZE TO GENERIC OVERLOADING
	
	public <T> T deserialize(String data, Generic<T> generic)
	{
		return deserialize(new StringReader(data), generic);
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
	
	public <T> T deserialize(InputStream input, Type type)
	{
		return deserialize(new InputStreamReader(input, UTF_8), type);
	}
	
	public abstract <T> T deserialize(Reader reader, Type type);
	
	
	
	
	// DESERIALIZE TO MAP OVERLOADING
	
	public LinkedHashMap<String, Object> deserialize(String data)
	{
		return deserialize(new StringReader(data));
	}
	
	public LinkedHashMap<String, Object> deserialize(InputStream input)
	{
		return deserialize(new InputStreamReader(input, UTF_8));
	}
	
	public abstract LinkedHashMap<String, Object> deserialize(Reader reader);
	
	
	
	
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
