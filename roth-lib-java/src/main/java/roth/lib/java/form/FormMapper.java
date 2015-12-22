package roth.lib.java.form;

import static roth.lib.java.util.ReflectionUtil.getFieldValue;
import static roth.lib.java.util.ReflectionUtil.getTypeClass;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertyReflector;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.util.IoUtil;
import roth.lib.java.util.UrlUtil;

public class FormMapper extends Mapper
{
	
	public FormMapper()
	{
		this(MapperReflector.get());
	}
	
	public FormMapper(MapperConfig mapperConfig)
	{
		this(MapperReflector.get(), mapperConfig);
	}
	
	public FormMapper(MapperReflector mapperReflector)
	{
		this(mapperReflector, MapperConfig.get());
	}
	
	public FormMapper(MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		super(MapperType.FORM, mapperReflector, mapperConfig);
	}
	
	@Override
	public void serialize(Object value, OutputStream output)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		if(!(value instanceof FormBoundary))
		{
			super.serialize(value, output);
		}
		else
		{
			/*
			try
			{
				String boundary = ((FormBoundary) value).getBoundary();
				MultiPartFormOutputStream formOutput = new MultiPartFormOutputStream(output, boundary);
				for(PropertyReflector propertyReflector : getMapperReflector().getPropertyReflectors(value.getClass()))
				{
					if(!hasContext() || !propertyReflector.getExcludes().contains(getContext()))
					{
						Class<?> propertyClass = propertyReflector.getFieldClass();
						String propertyName = propertyReflector.getPropertyName();
						if(propertyName != null)
						{
							Object fieldValue = getFieldValue(propertyReflector.getField(), value);
							Serializer<?> serializer = getMapperConfig().getSerializer(propertyClass);
							if(serializer != null)
							{
								String serializedValue = null;
								if(fieldValue != null)
								{
									serializedValue = serializer.serialize(fieldValue, propertyReflector.getTimeFormat());
								}
								else if(getMapperConfig().isSerializeNulls())
								{
									serializedValue = BLANK;
								}
								if(serializedValue != null)
								{
									formOutput.writeField(propertyName, serializedValue);
								}
							}
							else if(fieldValue instanceof FormData)
							{
								FormData formData = (FormData) fieldValue;
								formOutput.writeFile(propertyName, formData.getContentType(), formData.getFilename(), formData.getOutput().toByteArray());
							}
							else if(fieldValue instanceof FormFile)
							{
								FormFile formFile = (FormFile) fieldValue;
								formOutput.writeFile(propertyName, formFile.getContentType(), formFile.getFile());
							}
						}
					}
				}
				formOutput.close();
			}
			catch(IOException e)
			{
				throw new FormException(e);
			}
			*/
		}
	}
	
	@Override
	public void serialize(Object value, Writer writer)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		try
		{
			String seperator = BLANK;
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
			for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
			{
				if(!hasContext() || !propertyReflector.isExcluded(getContext()))
				{
					Class<?> propertyClass = propertyReflector.getFieldClass();
					String propertyName = propertyReflector.getPropertyName(getMapperType());
					if(propertyName != null)
					{
						Serializer<?> serializer = getMapperConfig().getSerializer(propertyClass);
						if(serializer != null)
						{
							String serializedValue = null;
							Object fieldValue = getFieldValue(propertyReflector.getField(), value);
							if(fieldValue != null)
							{
								serializedValue = serializer.serialize(fieldValue, propertyReflector.getTimeFormat());
							}
							else if(getMapperConfig().isSerializeNulls())
							{
								serializedValue = BLANK;
							}
							if(serializedValue != null)
							{
								writer.write(seperator);
								writer.write(propertyName);
								writer.write(EQUAL);
								writer.write(UrlUtil.encode(serializedValue));
								if(BLANK.equals(seperator))
								{
									seperator += AMPERSAND;
									if(isPrettyPrint())
									{
										seperator += NEW_LINE;
									}
								}
							}
						}
					}
				}
			}
			writer.flush();
		}
		catch(IOException e)
		{
			throw new FormException(e);
		}
	}
	
	@Override
	public void serialize(Map<String, ?> map, Writer writer)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		try
		{
			String seperator = BLANK;
			for(Entry<String, ?> entry : map.entrySet())
			{
				String propertyName = entry.getKey();
				Object fieldValue = entry.getValue();
				String serializedValue = null;
				if(fieldValue != null)
				{
					Serializer<?> serializer = getMapperConfig().getSerializer(fieldValue.getClass());
					if(serializer != null)
					{
						serializedValue = serializer.serialize(fieldValue, null);
					}
				}
				else if(getMapperConfig().isSerializeNulls())
				{
					serializedValue = BLANK;
				}
				if(serializedValue != null)
				{
					writer.write(seperator);
					writer.write(propertyName);
					writer.write(EQUAL);
					writer.write(UrlUtil.encode(serializedValue));
					if(BLANK.equals(seperator))
					{
						seperator += AMPERSAND;
						if(isPrettyPrint())
						{
							seperator += NEW_LINE;
						}
					}
				}
			}
			writer.flush();
		}
		catch(IOException e)
		{
			throw new FormException(e);
		}
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type)
	{
		setCallbacks(callbacks);
		T entity = null;
		try
		{
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
			Class<T> klass = getTypeClass(type);
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			entity = constructor.newInstance();
			int b;
			char c;
			String name = BLANK;
			StringBuilder builder = new StringBuilder();
			while((b = reader.read()) > 0)
			{
				c = (char) b;
				switch(c)
				{
					case NEW_LINE:
					{
						break;
					}
					case EQUAL:
					{
						name = builder.toString();
						builder.setLength(0);
						break;
					}
					case AMPERSAND:
					{
						setValue(entity, entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector()), UrlUtil.decode(builder.toString()));
						name = BLANK;
						builder.setLength(0);
						break;
					}
					default:
					{
						builder.append(c);
						break;
					}
				}
			}
			setValue(entity, entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector()), UrlUtil.decode(builder.toString()));
		}
		catch(Exception e)
		{
			throw new FormException(e);
		}
		return entity;
	}
	
	protected void setValue(Object model, PropertyReflector propertyReflector, String value) throws Exception
	{
		if(propertyReflector != null)
		{
			Deserializer<?> deserializer = getMapperConfig().getDeserializer(propertyReflector.getFieldClass());
			if(deserializer != null)
			{
				propertyReflector.getField().set(model, deserializer.deserialize(value, propertyReflector.getTimeFormat(), propertyReflector.getFieldClass()));
			}
		}
	}
	
	@Override
	public LinkedHashMap<String, Object> deserialize(Reader reader)
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			int b;
			char c;
			String name = null;
			StringBuilder builder = new StringBuilder();
			while((b = reader.read()) > 0)
			{
				c = (char) b;
				switch(c)
				{
					case NEW_LINE:
					{
						break;
					}
					case EQUAL:
					{
						name = builder.toString();
						builder.setLength(0);
						break;
					}
					case AMPERSAND:
					{
						map.put(name, UrlUtil.decode(builder.toString()));
						name = null;
						builder.setLength(0);
						break;
					}
					default:
					{
						builder.append(c);
						break;
					}
				}
			}
			map.put(name, UrlUtil.decode(builder.toString()));
		}
		catch(Exception e)
		{
			throw new FormException(e);
		}
		return map;
	}
	
	public LinkedHashMap<String, String> convert(Object value)
	{
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
		for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
		{
			if(!hasContext() || !propertyReflector.getExcludes().contains(getContext()))
			{
				Class<?> propertyClass = propertyReflector.getFieldClass();
				String propertyName = propertyReflector.getPropertyName(getMapperType());
				if(propertyName != null)
				{
					Serializer<?> serializer = getMapperConfig().getSerializer(propertyClass);
					if(serializer != null)
					{
						String serializedValue = null;
						Object fieldValue = getFieldValue(propertyReflector.getField(), value);
						if(fieldValue != null)
						{
							serializedValue = serializer.serialize(fieldValue, propertyReflector.getTimeFormat());
						}
						else if(getMapperConfig().isSerializeNulls())
						{
							serializedValue = BLANK;
						}
						if(serializedValue != null)
						{
							map.put(propertyName, serializedValue);
						}
					}
				}
			}
		}
		return map;
	}
	
	@Override
	public String prettyPrint(Reader reader)
	{
		try
		{
			String form = IoUtil.toString(reader);
			return form.replaceAll("&(?!\\r|\\n)", "&\n");
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
}
