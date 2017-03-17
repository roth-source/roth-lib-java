package roth.lib.java.table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Collection;

import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperException;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertyReflector;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.time.TimeZone;
import roth.lib.java.util.ReflectionUtil;

public class FixedWidthTableMapper extends Mapper
{
	
	public FixedWidthTableMapper()
	{
		this(MapperReflector.get());
	}
	
	public FixedWidthTableMapper(MapperConfig mapperConfig)
	{
		this(MapperReflector.get(), mapperConfig);
	}
	
	public FixedWidthTableMapper(MapperReflector mapperReflector)
	{
		this(mapperReflector, MapperConfig.get());
	}
	
	public FixedWidthTableMapper(MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		super(MapperType.TABLE, mapperReflector, mapperConfig);
	}
	
	@Override
	public void serialize(java.util.Map<String, ?> map, Writer writer)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void serialize(Object value, Writer writer)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		try
		{
			if(ReflectionUtil.isArray(value.getClass()) || ReflectionUtil.isCollection(value.getClass()))
			{
				List<?> values = ReflectionUtil.asCollection(value);
				if(!values.isEmpty())
				{
					EntityReflector entityReflector = getMapperReflector().getEntityReflector(values.getFirst().getClass());
					writeHeader(writer, entityReflector);
					writeArray(writer, values, entityReflector);
				}
				else
				{
					throw new TableException("Value array is empty");
				}
			}
			else
			{
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
				if(entityReflector != null)
				{
					writeHeader(writer, entityReflector);
					writeEntity(writer, value, entityReflector);
				}
				else
				{
					throw new TableException("Value is not an entity");
				}
			}
			writer.flush();
		}
		catch(Exception e)
		{
			throw new TableException(e);
		}
	}
	
	protected void writeHeader(Writer writer, EntityReflector entityReflector) throws Exception
	{
		if(getMapperConfig().isTableHeader())
		{
			String seperator = BLANK;
			for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
			{
				writer.write(seperator);
				writer.write(propertyReflector.getPropertyName(getMapperType()));
				seperator = String.valueOf(getMapperConfig().getDelimiter());
			}
			writer.write(CARRIAGE_RETURN);
			writer.write(NEW_LINE);
		}
	}
	
	protected void writeArray(Writer writer, List<?> values, EntityReflector entityReflector) throws Exception
	{
		for(Object value : values)
		{
			writeEntity(writer, value, entityReflector);
		}
	}
	
	protected void writeEntity(Writer writer, Object value, EntityReflector entityReflector) throws Exception
	{
		String seperator = BLANK;
		for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
		{
			writer.write(seperator);
			writeField(writer, ReflectionUtil.getFieldValue(propertyReflector.getField(), value), propertyReflector);
			seperator = String.valueOf(getMapperConfig().getDelimiter());
		}
		writer.write(CARRIAGE_RETURN);
		writer.write(NEW_LINE);
	}
	
	protected void writeField(Writer writer, Object value, PropertyReflector propertyReflector) throws Exception
	{
		String serializedValue = null;
		if(value != null)
		{
			Serializer<?> serializer = propertyReflector.getSerializer(getMapperType(), getMapperReflector(), getMapperConfig());
			if(serializer != null)
			{
				TimeZone timeZone = getTimeZone(propertyReflector);
				String timeFormat = getTimeFormat(propertyReflector);
				serializedValue = serializer.serialize(value, timeZone, timeFormat);
			}
		}
		if(serializedValue != null)
		{
			if(isEscaped(serializedValue))
			{
				writer.write(getMapperConfig().getQualifier());
				writer.write(escape(serializedValue));
				writer.write(getMapperConfig().getQualifier());
			}
			else
			{
				writer.write(serializedValue);
			}
		}
	}
	
	protected boolean isEscaped(String value)
	{
		boolean escaped = false;
		List<Character> escapeCharacters = new List<Character>(getMapperConfig().getDelimiter(), getMapperConfig().getQualifier(), CARRIAGE_RETURN, NEW_LINE);
		for(Character escapeCharacter : escapeCharacters)
		{
			if(value.contains(String.valueOf(escapeCharacter)))
			{
				escaped = true;
				break;
			}
		}
		return escaped;
	}
	
	protected String escape(String value)
	{
		String qualifier = String.valueOf(getMapperConfig().getQualifier());
		return value.replace(qualifier, qualifier + qualifier);
	}
	
	@Override
	public Map<String, Object> deserialize(Reader reader)
	{
		throw new UnsupportedOperationException();
	}
	
	public <T> List<T> deserializeList(String data, Class<T> klass)
	{
		return deserializeList(new StringReader(data), klass);
	}
	
	public <T> List<T> deserializeList(File file, Class<T> klass)
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			return deserializeList(input, klass);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public <T> List<T> deserializeList(InputStream input, Class<T> klass)
	{
		return deserializeList(new InputStreamReader(input, UTF_8), klass);
	}
	
	public <T> List<T> deserializeList(Reader reader, Class<T> klass)
	{
		reader = reader instanceof BufferedReader ? reader : new BufferedReader(reader); 
		List<T> list = new List<T>();
		try
		{
			int row = 0;
			T entity = null;
			while((entity = readEntity(reader, klass, ++row)) != null)
			{
				list.add(entity);
			}
		}
		catch(TableException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new TableException(e);
		}
		return list;
	}

	public List<List<String>> deserializeList(String data, List<Integer> widths)
	{
		return deserializeList(new StringReader(data), widths);
	}
	
	public List<List<String>> deserializeList(File file, List<Integer> widths)
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			return deserializeList(input, widths);
		}
		catch(IOException e)
		{
			throw new MapperException(e);
		}
	}
	
	public List<List<String>> deserializeList(InputStream input, List<Integer> widths)
	{
		return deserializeList(new InputStreamReader(input, UTF_8), widths);
	}
	
	public List<List<String>> deserializeList(Reader reader, List<Integer> widths)
	{
		reader = reader instanceof BufferedReader ? reader : new BufferedReader(reader); 
		List<List<String>> list = new List<>();
		try
		{
			int row = 0;
			while(!isEof(reader))
			{
				List<String> recordList = readList(reader, widths, ++row);
				if(recordList != null)
				{
					list.add(recordList);
				}
			}
		}
		catch(TableException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new TableException(e);
		}
		return list;
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type)
	{
		reader = reader instanceof BufferedReader ? reader : new BufferedReader(reader); 
		T model = null;
		try
		{
			if(ReflectionUtil.isArray(type) || ReflectionUtil.isCollection(type))
			{
				model = readArray(reader, type);
			}
			else
			{
				model = readEntity(reader, type, 1);
			}
		}
		catch(TableException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new TableException(e);
		}
		return model;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readArray(Reader reader, Type type) throws Exception
	{
		if(ReflectionUtil.isCollection(type))
		{
			return readCollection(reader, type);
		}
		else if(ReflectionUtil.isArray(type))
		{
			Type elementType = ReflectionUtil.getElementType(type);
			List<E> collection = readCollection(reader, type);
			E[] array = (E[]) Array.newInstance(ReflectionUtil.getTypeClass(elementType), collection.size());
			int i = 0;
			for(E element : collection)
			{
				array[i++] = element;
			}
			return (T) array;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readCollection(Reader reader, Type type) throws Exception
	{
		Collection<E> collection = null;
		Class<T> klass = ReflectionUtil.getTypeClass(type);
		Type elementType = ReflectionUtil.getElementType(type);
		if(klass.isAssignableFrom(List.class) || ReflectionUtil.isArray(klass))
		{
			collection = new List<E>();
		}
		else
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			collection = (Collection<E>) constructor.newInstance();
		}
		int row = 0;
		E entity = null;
		while((entity = readEntity(reader, elementType, ++row)) != null)
		{
			collection.add(entity);
		}
		return (T) collection;
	}
	
	protected <T> T readEntity(Reader reader, Type type, int row) throws Exception
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
		List<PropertyReflector> propertyReflectors = entityReflector.getPropertyReflectors(getMapperType());
		Class<T> klass = ReflectionUtil.getTypeClass(type);
		Constructor<T> constructor = klass.getDeclaredConstructor();
		constructor.setAccessible(true);
		T model = constructor.newInstance();
		for(PropertyReflector propertyReflector : propertyReflectors)
		{
			read(reader, propertyReflector.getWidth());
		}
		readUntil(reader, NEW_LINE, CARRIAGE_RETURN);
		return model;
	}
	
	protected List<String> readList(Reader reader, List<Integer> widths, int row) throws Exception
	{
		List<String> list = new List<String>().allowNull();
		try
		{
			for(Integer width : widths)
			{
				String value = read(reader, width);
				list.add(value);
			}
		}
		catch(Exception e)
		{
			list = null;
		}
		readUntil(reader, NEW_LINE, CARRIAGE_RETURN);
		return list;
	}
	
	protected boolean isEof(Reader reader)
	{
		boolean eof = false;
		try
		{
			reader.mark(1);
			int b = reader.read();
			eof = b == -1;
			reader.reset();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return eof;
	}
	
	protected String read(Reader reader, int width)
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			int i = 0;
			int b;
			char c;
			while(i++ < width && (b = reader.read()) > -1)
			{
				c = (char) b;
				builder.append(c);
			}
		}
		catch(Exception e)
		{
			
		}
		String value = builder.toString();
		return !value.isEmpty() ? value : null;
	}
	
	@Override
	public String prettyPrint(Reader reader)
	{
		throw new UnsupportedOperationException();
	}
	
}
