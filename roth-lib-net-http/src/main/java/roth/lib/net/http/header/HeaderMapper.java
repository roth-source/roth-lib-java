package roth.lib.net.http.header;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.annotation.Property;
import roth.lib.map.Deserializer;
import roth.lib.map.Mapper;
import roth.lib.map.PropertyField;
import roth.lib.map.SerialMapper;
import roth.lib.map.mapper.PropertyMapper;
import roth.lib.map.util.MapperUtil;

public class HeaderMapper extends SerialMapper<HeaderConfig>
{
	protected static HeaderMapper instance;
	
	public HeaderMapper()
	{
		super();
		propertyMappers.add(new PropertyMapper<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Field field, Property property)
			{
				if(property != null)
				{
					if(isValid(property.name()))
					{
						return property.name();
					}
				}
				return null;
			}
			
			@Override
			public boolean isEntityName(Field field, Property property)
			{
				return property != null && property.entityName();
			}
		});
	}
	
	public static HeaderMapper get()
	{
		if(instance == null)
		{
			instance = new HeaderMapper();
		}
		return instance;
	}
	
	public static void set(HeaderMapper newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public HeaderConfig defaultConfig()
	{
		return HeaderConfig.get();
	}
	
	@Override
	public void serialize(Object object, Writer writer, HeaderConfig config)
	{
		if(object != null)
		{
			for(PropertyField propertyField : getPropertyFields(object.getClass()))
			{
				try
				{
					String name = propertyField.getPropertyName();
					Object value = propertyField.getField().get(object);
					if(value instanceof Collection)
					{
						for(Object element : (Collection<?>) value)
						{
							writeHeader(writer, config, name, element);
						}
					}
					else
					{
						writeHeader(writer, config, name, value);
					}
				}
				catch(Exception e)
				{
					
				}
			}
			if(object instanceof Headers)
			{
				serialize((Map<String, ?>) ((Headers) object).getHeaderMap(), writer, config);
			}
		}
	}
	
	@Override
	public void serialize(Map<String, ?> map, Writer writer, HeaderConfig config)
	{
		if(map != null)
		{
			for(Entry<String, ?> entry : map.entrySet())
			{
				try
				{
					String name = entry.getKey();
					Object value = entry.getValue();
					writeHeader(writer, config, name, value);
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
	
	public void request(Object object, HttpURLConnection connection)
	{
		request(object, connection, defaultConfig());
	}
	
	public void request(Object object, HttpURLConnection connection, HeaderConfig config)
	{
		if(object != null)
		{
			for(PropertyField propertyField : getPropertyFields(object.getClass()))
			{
				try
				{
					String name = propertyField.getPropertyName();
					Object value = propertyField.getField().get(object);
					if(value instanceof Collection)
					{
						for(Object element : (Collection<?>) value)
						{
							connection.addRequestProperty(name, element.toString());
						}
					}
					else
					{
						connection.setRequestProperty(name, value.toString());
					}
				}
				catch(Exception e)
				{
					
				}
			}
			if(object instanceof Headers)
			{
				request(((Headers) object).getHeaderMap(), connection);
			}
		}
	}
	
	public void request(Map<String, ?> map, HttpURLConnection connection)
	{
		request(map, connection, defaultConfig());
	}
	
	public void request(Map<String, ?> map, HttpURLConnection connection, HeaderConfig config)
	{
		if(map != null)
		{
			for(Entry<String, ?> entry : map.entrySet())
			{
				try
				{
					String name = entry.getKey();
					Object value = entry.getValue();
					if(value instanceof Collection)
					{
						for(Object element : (Collection<?>) value)
						{
							connection.addRequestProperty(name, element.toString());
						}
					}
					else
					{
						connection.setRequestProperty(name, value.toString());
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
	
	protected void writeHeader(Writer writer, HeaderConfig config, String name, Object value) throws IOException
	{
		if(name != null && value != null)
		{
			writer.write(name);
			writer.write(COLON);
			writer.write(value.toString());
			writer.write(CARRIAGE_RETURN);
			writer.write(NEW_LINE);
		}
	}
	
	@Override
	public <T> T deserialize(InputStream input, Class<T> klass, HeaderConfig config)
	{
		T model = null;
		try
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			model = constructor.newInstance();
			LinkedHashMap<String, PropertyField> propertyNameFieldMap = getPropertyNameFieldMap(klass);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int b;
			read: while((b = input.read()) > -1)
			{
				switch(b)
				{
					case COLON:
					{
						String name = new String(output.toByteArray(), UTF_8);
						output.reset();
						PropertyField propertyField = propertyNameFieldMap.get(name);
						if(propertyField != null)
						{
							Field field = propertyField.getField();
							Type fieldType = propertyField.getFieldType();
							Class<?> fieldClass = propertyField.getFieldClass();
							Object value = readValue(input, config, fieldType);
							if(value != null && value.getClass().equals(fieldClass))
							{
								field.set(model, value);
							}
							else if(model instanceof Headers)
							{
								((Headers) model).setHeader(name, value.toString());
							}
						}
						else if(model instanceof Headers)
						{
							Object value = readValue(input, config, String.class);
							if(value != null)
							{
								((Headers) model).setHeader(name, value.toString());
							}
						}
						break;
					}
					case CARRIAGE_RETURN:
					{
						break;
					}
					case NEW_LINE:
					{
						if(output.size() == 0)
						{
							break read;
						}
						else
						{
							break;
						}
					}
					default:
					{
						output.write(b);
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			throw new HeaderException(e);
		}
		return model;
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type, HeaderConfig config)
	{
		throw new HeaderException("deserialize headers from reader not implemented");
	}
	
	@Override
	public LinkedHashMap<String, Object> deserialize(Reader reader, HeaderConfig config)
	{
		throw new HeaderException("deserialize map not implemented");
	}
	
	protected Object readValue(InputStream input, HeaderConfig config, Type type) throws IOException
	{
		Class<?> klass = MapperUtil.getClass(type);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int b;
		read: while((b = input.read()) > -1)
		{
			switch(b)
			{
				case CARRIAGE_RETURN:
				{
					break;
				}
				case NEW_LINE:
				{
					break read;
				}
				default:
				{
					output.write(b);
					break;
				}
			}
		}
		String value = new String(output.toByteArray(), Mapper.UTF_8);
		Deserializer<?> deserializer = config.getDeserializer(klass);
		if(value != null && deserializer != null)
		{
			Object object = deserializer.deserialize(value.trim(), klass);
			if(object != null)
			{
				return object;
			}
			else
			{
				return value.trim();
			}
		}
		else
		{
			return value;
		}
	}
	
	public <T> T response(HttpURLConnection connection, Class<T> klass)
	{
		return response(connection, klass, defaultConfig());
	}
	
	@SuppressWarnings("unchecked")
	public <T> T response(HttpURLConnection connection, Class<T> klass, HeaderConfig config)
	{
		T model = null;
		try
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			model = constructor.newInstance();
			LinkedHashMap<String, PropertyField> propertyNameFieldMap = getPropertyNameFieldMap(klass);
			for(Entry<String, List<String>> headerEntry : connection.getHeaderFields().entrySet())
			{
				String name = headerEntry.getKey();
				List<String> values = headerEntry.getValue();
				PropertyField propertyField = propertyNameFieldMap.get(name);
				if(propertyField != null)
				{
					Field field = propertyField.getField();
					Type fieldType = propertyField.getFieldType();
					Class<?> fieldClass = propertyField.getFieldClass();
					if(isCollection(fieldType))
					{
						Class<?> elementClass = getElementClass(fieldType);
						Deserializer<?> deserializer = config.getDeserializer(elementClass);
						Collection<Object> fieldObject = (Collection<Object>) field.get(model);
						if(deserializer != null && fieldObject != null)
						{
							for(String value : values)
							{
								Object element = deserializer.deserialize(value.trim(), elementClass);
								if(element != null)
								{
									fieldObject.add(element);
								}
							}
						}
					}
					else if(values.size() > 0)
					{
						Deserializer<?> deserializer = config.getDeserializer(fieldClass);
						if(deserializer != null)
						{
							Object value = deserializer.deserialize(values.get(0).trim(), fieldClass);
							if(value != null)
							{
								field.set(model, value);
							}
						}
					}
				}
				else if(model instanceof Headers)
				{
					if(values.size() == 1)
					{
						((Headers) model).setHeader(name, values.get(0));
					}
					else if(values.size() > 1)
					{
						((Headers) model).setHeader(name, values);
					}
				}
			}
		}
		catch(Exception e)
		{
			throw new HeaderException(e);
		}
		return model;
	}
	
}
