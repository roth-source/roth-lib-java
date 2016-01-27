package roth.lib.java.json;

import static roth.lib.java.util.ReflectionUtil.asCollection;
import static roth.lib.java.util.ReflectionUtil.asMap;
import static roth.lib.java.util.ReflectionUtil.getElementType;
import static roth.lib.java.util.ReflectionUtil.getFieldValue;
import static roth.lib.java.util.ReflectionUtil.getKeyType;
import static roth.lib.java.util.ReflectionUtil.getTypeClass;
import static roth.lib.java.util.ReflectionUtil.isArray;
import static roth.lib.java.util.ReflectionUtil.isCollection;
import static roth.lib.java.util.ReflectionUtil.isMap;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import roth.lib.java.lang.Map;
import java.util.Map.Entry;

import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.lang.List;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertiesReflector;
import roth.lib.java.reflector.PropertyReflector;
import roth.lib.java.serializer.Serializer;

public class JsonMapper extends Mapper
{
	protected static String NULL 	= "null";
	protected static String TRUE 	= "true";
	protected static String FALSE	= "false";
	
	public JsonMapper()
	{
		this(MapperReflector.get());
	}
	
	public JsonMapper(MapperConfig mapperConfig)
	{
		this(MapperReflector.get(), mapperConfig);
	}
	
	public JsonMapper(MapperReflector mapperReflector)
	{
		this(mapperReflector, MapperConfig.get());
	}
	
	public JsonMapper(MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		super(MapperType.JSON, mapperReflector, mapperConfig);
	}
	
	@Override
	public void serialize(Object value, Writer writer)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		try
		{
			if(isArray(value.getClass()) || isCollection(value.getClass()))
			{
				List<?> values = asCollection(value);
				if(!values.isEmpty())
				{
					writeArray(writer, values, null);
				}
				else
				{
					writer.write(LEFT_BRACKET);
					writer.write(RIGHT_BRACKET);
				}
			}
			else
			{
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
				if(entityReflector != null)
				{
					writeEntity(writer, value, entityReflector);
				}
				else
				{
					throw new JsonException("Value is not an entity");
				}
			}
			writer.flush();
		}
		catch(IOException e)
		{
			throw new JsonException(e);
		}
	}
	
	@Override
	public void serialize(java.util.Map<String, ?> map, Writer writer)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		try
		{
			writeMap(writer, map, null);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new JsonException(e);
		}
	}
	
	protected void writeEntity(Writer writer, Object value, EntityReflector entityReflector) throws IOException
	{
		writer.write(LEFT_BRACE);
		String seperator = BLANK;
		PropertiesReflector propertiesReflector = entityReflector.getPropertiesReflector();
		if(propertiesReflector != null && propertiesReflector.isFirst())
		{
			for(Entry<String, Object> entry : propertiesReflector.getMap(value).entrySet())
			{
				seperator = writeProperty(writer, entry.getKey(), entry.getValue(), seperator, null);
			}
		}
		for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
		{
			if(!propertyReflector.isAttribute() || !hasContext() || !propertyReflector.isExcluded(getContext()))
			{
				String propertyName = propertyReflector.getPropertyName(getMapperType());
				Object propertyValue = getFieldValue(propertyReflector.getField(), value);
				seperator = writeProperty(writer, propertyName, propertyValue, seperator, propertyReflector);
			}
		}
		if(propertiesReflector != null && !propertiesReflector.isFirst())
		{
			for(Entry<String, Object> entry : propertiesReflector.getMap(value).entrySet())
			{
				seperator = writeProperty(writer, entry.getKey(), entry.getValue(), seperator, null);
			}
		}
		writeNewLine(writer);
		writer.write(RIGHT_BRACE);
	}
	
	protected String writeProperty(Writer writer, String name, Object value, String seperator, PropertyReflector propertyReflector) throws IOException
	{
		incrementTabs();
		if(name != null && (value != null || getMapperConfig().isSerializeNulls()))
		{
			if(value == null)
			{
				seperator = writeSeperator(writer, seperator);
				writeNewLine(writer);
				writePropertyName(writer, name);
				writer.write(NULL);
			}
			else if(getMapperReflector().isEntity(value.getClass()))
			{
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
				String propertyName = entityReflector.getPropertyName();
				if(propertyName != null)
				{
					name = propertyName;
				}
				seperator = writeSeperator(writer, seperator);
				writeNewLine(writer);
				writePropertyName(writer, name);
				writeNewLine(writer);
				writeEntity(writer, value, entityReflector);
			}
			else if(isArray(value.getClass()) || isCollection(value.getClass()))
			{
				List<?> values = asCollection(value);
				if(!values.isEmpty())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writePropertyName(writer, name);
					writeNewLine(writer);
					writeArray(writer, values, propertyReflector);
				}
				else if(getMapperConfig().isSerializeEmptyArray())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writePropertyName(writer, name);
					writer.write(LEFT_BRACKET);
					writer.write(RIGHT_BRACKET);
				}
			}
			else if(isMap(value.getClass()))
			{
				Map<?, ?> valueMap = asMap(value);
				if(!valueMap.isEmpty())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writePropertyName(writer, name);
					writeNewLine(writer);
					writeMap(writer, valueMap, propertyReflector);
				}
				else if(getMapperConfig().isSerializeEmptyMap())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writePropertyName(writer, name);
					writer.write(LEFT_BRACE);
					writer.write(RIGHT_BRACE);
				}
			}
			else if(isSerializable(value.getClass()))
			{
				Serializer<?> serializer = getSerializer(value.getClass());
				String timeFormat = getTimeFormat(propertyReflector);
				boolean escapable = serializer.isEscapable(value, timeFormat);
				String serializedValue = serializer.serialize(value, timeFormat);
				if(serializedValue != null)
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writePropertyName(writer, name);
					writeValue(writer, serializedValue, escapable);
				}
				else if(getMapperConfig().isSerializeNulls())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writePropertyName(writer, name);
					writer.write(NULL);
				}
			}
		}
		decrementTabs();
		return seperator;
	}
	
	protected void writePropertyName(Writer writer, String propertyName) throws IOException
	{
		writer.write(QUOTE);
		writer.write(propertyName);
		writer.write(QUOTE);
		writer.write(COLON);
	}
	
	protected void writeArray(Writer writer, Collection<?> values, PropertyReflector propertyReflector) throws IOException
	{
		writer.write(LEFT_BRACKET);
		String seperator = BLANK;
		for(Object value : values)
		{
			if(value != null || getMapperConfig().isSerializeNulls())
			{
				if(value == null)
				{
					incrementTabs();
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writer.write(NULL);
					decrementTabs();
				}
				else if(getMapperReflector().isEntity(value.getClass()))
				{
					incrementTabs();
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer);
					writeEntity(writer, value, getMapperReflector().getEntityReflector(value.getClass()));
					decrementTabs();
				}
				else if(isArray(value.getClass()) || isCollection(value.getClass()))
				{
					List<?> arrayValues = asCollection(value);
					if(!arrayValues.isEmpty())
					{
						incrementTabs();
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer);
						writeArray(writer, arrayValues, propertyReflector);
						decrementTabs();
					}
					else if(getMapperConfig().isSerializeEmptyArray())
					{
						incrementTabs();
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer);
						writer.write(LEFT_BRACKET);
						writer.write(RIGHT_BRACKET);
						decrementTabs();
					}
				}
				else if(isMap(value.getClass()))
				{
					java.util.Map<?, ?> valueMap = asMap(value);
					if(!valueMap.isEmpty())
					{
						incrementTabs();
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer);
						writeMap(writer, valueMap, propertyReflector);
						decrementTabs();
					}
					else if(getMapperConfig().isSerializeEmptyMap())
					{
						incrementTabs();
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer);
						writer.write(LEFT_BRACE);
						writer.write(RIGHT_BRACE);
						decrementTabs();
					}
				}
				else if(isSerializable(value.getClass()))
				{
					Serializer<?> serializer = getSerializer(value.getClass());
					String timeFormat = getTimeFormat(propertyReflector);
					boolean escapable = serializer.isEscapable(value, timeFormat);
					String serializedValue = serializer.serialize(value, timeFormat);
					if(serializedValue != null)
					{
						incrementTabs();
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer);
						writeValue(writer, serializedValue, escapable);
						decrementTabs();
					}
					else if(getMapperConfig().isSerializeNulls())
					{
						incrementTabs();
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer);
						writer.write(NULL);
						decrementTabs();
					}
				}
			}
		}
		writeNewLine(writer);
		writer.write(RIGHT_BRACKET);
	}
	
	protected void writeMap(Writer writer, java.util.Map<?, ?> valueMap, PropertyReflector propertyReflector) throws IOException
	{
		writer.write(LEFT_BRACE);
		String seperator = BLANK;
		for(Entry<?, ?> valueEntry : valueMap.entrySet())
		{
			String name = null;
			Object key = valueEntry.getKey();
			if(key instanceof String)
			{
				name = (String) key;
			}
			else
			{
				Serializer<?> serializer = getSerializer(key.getClass());
				if(serializer != null)
				{
					String timeFormat = getTimeFormat(propertyReflector);
					name = serializer.serialize(key, timeFormat);
				}
			}
			if(name != null)
			{
				seperator = writeProperty(writer, name, valueEntry.getValue(), seperator, null);
			}
		}
		writeNewLine(writer);
		writer.write(RIGHT_BRACE);
	}
	
	protected void writeNewLine(Writer writer) throws IOException
	{
		if(isPrettyPrint())
		{
			writer.write(NEW_LINE);
			for(int i = 0; i < getTabs(); i++)
			{
				writer.write(TAB);
			}
		}
	}
	
	protected String writeSeperator(Writer writer, String seperator) throws IOException
	{
		writer.write(seperator);
		return BLANK.equals(seperator) ? String.valueOf(COMMA) : seperator;
	}
	
	protected void writeValue(Writer writer, String value, boolean escapable) throws IOException
	{
		if(value == null)
		{
			writer.write(NULL);
		}
		else if(!escapable)
		{
			writer.write(value);
		}
		else
		{
			writer.write(QUOTE);
			for(char c : value.toCharArray())
			{
				switch(c)
				{
					case QUOTE:
					{
						writer.write("\\\"");
						break;
					}
					case BACKSLASH:
					{
						writer.write("\\\\");
						break;
					}
					case SLASH:
					{
						writer.write("\\/");
						break;
					}
					case TAB:
					{
						writer.write("\\t");
						break;
					}
					case NEW_LINE:
					{
						writer.write("\\n");
						break;
					}
					case CARRIAGE_RETURN:
					{
						writer.write("\\r");
						break;
					}
					case BACKSPACE:
					{
						writer.write("\\b");
						break;
					}
					case FORM_FEED:
					{
						writer.write("\\f");
						break;
					}
					default:
					{
						writer.write(c);
						break;
					}
				}
			}
			writer.write(QUOTE);
		}
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type)
	{
		T model = null;
		try
		{
			if(isArray(type))
			{
				readUntil(reader, LEFT_BRACKET);
				model = readArray(reader, type, null);
			}
			else if(isCollection(type))
			{
				readUntil(reader, LEFT_BRACKET);
				model = readCollection(reader, type, null);
			}
			else
			{
				readUntil(reader, LEFT_BRACE);
				model = readEntity(reader, type);
			}
		}
		catch(Exception e)
		{
			throw new JsonException(e);
		}
		return model;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> deserialize(Reader reader)
	{
		Map<String, Object> map;
		try
		{
			readUntil(reader, LEFT_BRACE);
			map = (Map<String, Object>) readMap(reader, Map.class, null);
		}
		catch(Exception e)
		{
			throw new JsonException(e);
		}
		return map;
	}
	
	protected <T> T readEntity(Reader reader, Type type) throws Exception
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
		Class<T> klass = getTypeClass(type);
		Constructor<T> constructor = klass.getDeclaredConstructor();
		constructor.setAccessible(true);
		T model = constructor.newInstance();
		int b;
		char c;
		String name = null;
		StringBuilder builder = new StringBuilder();
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case SPACE:
				case TAB:
				case NEW_LINE:
				case CARRIAGE_RETURN:
				case FORM_FEED:
				case BACKSPACE:
				{
					break;
				}
				case QUOTE:
				case SINGLE_QUOTE:
				{
					if(name == null)
					{
						name = readEscaped(reader, c);
					}
					else
					{
						String value = readEscaped(reader, c);
						PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
						if(propertyReflector != null)
						{
							Field field = propertyReflector.getField();
							Class<?> fieldClass = propertyReflector.getFieldClass();
							Deserializer<?> deserializer = getDeserializer(fieldClass);
							if(deserializer != null)
							{
								String timeFormat = getTimeFormat(propertyReflector);
								field.set(model, deserializer.deserialize(value, timeFormat, fieldClass));
								setDeserializedName(model, propertyReflector.getFieldName());
							}
						}
						else
						{
							PropertiesReflector propertiesReflector = entityReflector.getPropertiesReflector();
							if(propertiesReflector != null)
							{
								propertiesReflector.put(model, name, value);
							}
						}
						name = null;
					}
					builder.setLength(0);
					break;
				}
				case COLON:
				{
					if(name != null)
					{
						PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
						if(propertyReflector == null)
						{
							PropertiesReflector propertiesReflector = entityReflector.getPropertiesReflector();
							if(propertiesReflector == null)
							{
								c = readUnmapped(reader);
								name = null;
								if(RIGHT_BRACE == c)
								{
									break read;
								}
							}
						}
					}
					builder.setLength(0);
					break;
				}
				case COMMA:
				case RIGHT_BRACE:
				{
					if(name != null)
					{
						String value = builder.toString();
						PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
						if(propertyReflector != null)
						{
							Field field = propertyReflector.getField();
							Class<?> fieldClass = propertyReflector.getFieldClass();
							if(!NULL.equalsIgnoreCase(value))
							{
								Deserializer<?> deserializer = getDeserializer(fieldClass);
								if(deserializer != null)
								{
									String timeFormat = getTimeFormat(propertyReflector);
									field.set(model, deserializer.deserialize(value, timeFormat, fieldClass));
									setDeserializedName(model, propertyReflector.getFieldName());
								}
							}
							else
							{
								field.set(model, null);
								setDeserializedName(model, propertyReflector.getFieldName());
							}
						}
						else
						{
							PropertiesReflector propertiesReflector = entityReflector.getPropertiesReflector();
							if(propertiesReflector != null)
							{
								propertiesReflector.put(model, name, !NULL.equalsIgnoreCase(value) ? value : null);
							}
						}
						name = null;
					}
					builder.setLength(0);
					if(COMMA == c)
					{
						break;
					}
					else
					{
						break read;
					}
				}
				case LEFT_BRACE:
				{
					if(name != null)
					{
						PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
						if(propertyReflector != null)
						{
							Field field = propertyReflector.getField();
							Type fieldType = propertyReflector.getFieldType();
							if(getMapperReflector().isEntity(fieldType))
							{
								Object value = readEntity(reader, fieldType);
								field.set(model, value);
								setDeserializedName(model, propertyReflector.getFieldName());
							}
							else
							{
								Object value = readMap(reader, fieldType, propertyReflector);
								field.set(model, value);
								setDeserializedName(model, propertyReflector.getFieldName());
							}
						}
						else
						{
							Map<String, Object> value = readMap(reader, Map.class, null);
							PropertiesReflector propertiesReflector = entityReflector.getPropertiesReflector();
							if(propertiesReflector != null)
							{
								propertiesReflector.put(model, name, value);
							}
						}
						builder.setLength(0);
						name = null;
					}
					break;
				}
				case LEFT_BRACKET:
				{
					if(name != null)
					{
						PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
						if(propertyReflector != null)
						{
							Field field = propertyReflector.getField();
							Type fieldType = propertyReflector.getFieldType();
							Object value = readArray(reader, fieldType, propertyReflector);
							field.set(model, value);
							setDeserializedName(model, propertyReflector.getFieldName());
						}
						else
						{
							List<Object> value = readCollection(reader, List.class, null);
							PropertiesReflector propertiesReflector = entityReflector.getPropertiesReflector();
							if(propertiesReflector != null)
							{
								propertiesReflector.put(model, name, value);
							}
						}
						name = null;
					}
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
		return model;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, K, E> T readMap(Reader reader, Type type, PropertyReflector propertyReflector) throws Exception
	{
		Map<K, E> map = null;
		Class<T> klass = getTypeClass(type);
		Type keyType = getKeyType(type);
		Class<K> keyClass = getTypeClass(keyType);
		Type elementType = getElementType(type);
		if(klass.isAssignableFrom(Map.class))
		{
			map = new Map<K, E>();
		}
		else
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			map = (Map<K, E>) constructor.newInstance();
		}
		int b;
		char c;
		String name = null;
		K key = null;
		StringBuilder builder = new StringBuilder();
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case SPACE:
				case TAB:
				case NEW_LINE:
				case CARRIAGE_RETURN:
				case FORM_FEED:
				case BACKSPACE:
				{
					break;
				}
				case QUOTE:
				case SINGLE_QUOTE:
				{
					if(name == null)
					{
						name = readEscaped(reader, c);
						Deserializer<?> deserializer = getDeserializer(keyClass);
						if(deserializer != null)
						{
							key = (K) deserializer.deserialize(name, null);
						}
					}
					else
					{
						String value = readEscaped(reader, c);
						map.put(key, (E) value);
						key = null;
						name = null;
					}
					builder.setLength(0);
					break;
				}
				case COLON:
				{
					builder.setLength(0);
					break;
				}
				case COMMA:
				case RIGHT_BRACE:
				{
					if(name != null)
					{
						String value = builder.toString();
						if(NULL.equalsIgnoreCase(value))
						{
							map.put(key, null);
						}
						else if(TRUE.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value))
						{
							map.put(key, (E) new Boolean(value));
						}
						else
						{
							E number = (E) value;
							try
							{
								number = (E) new Double(value);
							}
							catch(Exception e)
							{
								
							}
							map.put(key, number);
						}
						builder.setLength(0);
						key = null;
						name = null;
					}
					builder.setLength(0);
					if(COMMA == c)
					{
						break;
					}
					else
					{
						break read;
					}
				}
				case LEFT_BRACE:
				{
					if(name != null)
					{
						if(getMapperReflector().isEntity(elementType))
						{
							E value = readEntity(reader, elementType);
							map.put(key, value);
						}
						else
						{
							E value = readMap(reader, elementType, propertyReflector);
							map.put(key, value);
						}
						key = null;
						name = null;
					}
					builder.setLength(0);
					break;
				}
				case LEFT_BRACKET:
				{
					if(name != null)
					{
						E value = readArray(reader, elementType, propertyReflector);
						map.put(key, value);
						key = null;
						name = null;
					}
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
		return (T) map;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readArray(Reader reader, Type type, PropertyReflector propertyReflector) throws Exception
	{
		if(isCollection(type))
		{
			return readCollection(reader, type, propertyReflector);
		}
		else if(isArray(type))
		{
			Type elementType = getElementType(type);
			List<E> collection = readCollection(reader, type, propertyReflector);
			E[] array = (E[]) Array.newInstance(getTypeClass(elementType), collection.size());
			for(int i = 0; i < collection.size(); i++)
			{
				array[i] = collection.get(i);
			}
			return (T) array;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readCollection(Reader reader, Type type, PropertyReflector propertyReflector) throws Exception
	{
		Collection<E> collection = null;
		Class<T> klass = getTypeClass(type);
		Type elementType = getElementType(type);
		Class<E> elementClass = getTypeClass(elementType);
		if(klass.isAssignableFrom(List.class) || isArray(klass))
		{
			collection = new List<E>();
		}
		else
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			collection = (Collection<E>) constructor.newInstance();
		}
		int b;
		char c;
		StringBuilder builder = new StringBuilder();
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case SPACE:
				case TAB:
				case NEW_LINE:
				case CARRIAGE_RETURN:
				case FORM_FEED:
				case BACKSPACE:
				{
					break;
				}
				case QUOTE:
				case SINGLE_QUOTE:
				{
					String value = readEscaped(reader, c);
					if(value != null && !value.trim().isEmpty() && !NULL.equalsIgnoreCase(value))
					{
						Deserializer<?> deserializer = getDeserializer(elementClass);
						if(deserializer != null)
						{
							String timeFormat = getTimeFormat(propertyReflector);
							Object object = deserializer.deserialize(value, timeFormat, elementClass);
							if(object != null && elementClass.isAssignableFrom(object.getClass()))
							{
								collection.add((E) object);
							}
						}
						else
						{
							try
							{
								collection.add((E) value);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					builder.setLength(0);
					break;
				}
				case COLON:
				{
					builder.setLength(0);
					break;
				}
				case COMMA:
				case RIGHT_BRACKET:
				{
					String value = builder.toString();
					if(value != null && !value.trim().isEmpty() && !NULL.equalsIgnoreCase(value))
					{
						Deserializer<?> deserializer = getDeserializer(elementClass);
						if(deserializer != null)
						{
							String timeFormat = getTimeFormat(propertyReflector);
							Object object = deserializer.deserialize(value, timeFormat, elementClass);
							if(object != null && elementClass.isAssignableFrom(object.getClass()))
							{
								collection.add((E) object);
							}
						}
						else
						{
							try
							{
								collection.add((E) value);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					builder.setLength(0);
					if(COMMA == c)
					{
						break;
					}
					else
					{
						break read;
					}
				}
				case LEFT_BRACE:
				{
					Object value = null;
					if(getMapperReflector().isEntity(elementType))
					{
						value = readEntity(reader, elementClass);
					}
					else
					{
						value = readMap(reader, elementClass, propertyReflector);
					}
					if(value != null && elementClass.isAssignableFrom(value.getClass()))
					{
						collection.add((E) value);
					}
					builder.setLength(0);
					break;
				}
				case LEFT_BRACKET:
				{
					Object value = readCollection(reader, elementClass, propertyReflector);
					if(value != null && elementClass.isAssignableFrom(value.getClass()))
					{
						collection.add((E) value);
					}
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
		return (T) collection;
	}
	
	protected String readEscaped(Reader reader, Character quote) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int b;
		char c;
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case BACKSLASH:
				{
					c = (char) reader.read();
					switch(c)
					{
						case 'b':
						{
							builder.append(BACKSPACE);
							break;
						}
						case 'f':
						{
							builder.append(FORM_FEED);
							break;
						}
						case 'n':
						{
							builder.append(NEW_LINE);
							break;
						}
						case 'r':
						{
							builder.append(CARRIAGE_RETURN);
							break;
						}
						case 't':
						{
							builder.append(TAB);
							break;
						}
						case 'u':
						{
							char[] buffer = new char[4];
							reader.read(buffer);
							char u = (char) Integer.parseInt(new String(buffer), 16);
							builder.append(u);
							break;
						}
						default:
						{
							builder.append(c);
							break;
						}
					}
					break;
				}
				case QUOTE:
				case SINGLE_QUOTE:
				{
					if(quote.equals(c))
					{
						break read;
					}
					else
					{
						builder.append(c);
						break;
					}
				}
				default:
				{
					builder.append(c);
					break;
				}
			}
		}
		return builder.toString();
	}
	
	protected char readUnmapped(Reader reader) throws IOException
	{
		char last = RIGHT_BRACE;
		int nested = 0;
		int b;
		char c;
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case LEFT_BRACKET:
				{
					nested++;
					break;
				}
				case LEFT_BRACE:
				{
					nested++;
					break;
				}
				case RIGHT_BRACKET:
				{
					nested--;
					break;
				}
				case RIGHT_BRACE:
				{
					if(nested <= 0)
					{
						last = RIGHT_BRACE;
						break read;
					}
					nested--;
					break;
				}
				case QUOTE:
				case SINGLE_QUOTE:
				{
					readEscaped(reader, c);
				}
				case COMMA:
				{
					if(nested <= 0)
					{
						last = COMMA;
						break read;
					}
					break;
				}
			}
		}
		return last;
	}
	
	@Override
	public String prettyPrint(Reader reader)
	{
		int tabs = 0;
		StringBuilder builder = new StringBuilder();
		try
		{
			boolean escaped = false;
			char escapeChar = QUOTE;
			int b;
			char c;
			char p = 0;
			while((b = reader.read()) > -1)
			{
				c = (char) b;
				switch(c)
				{
					case QUOTE:
					case SINGLE_QUOTE:
					{
						if(p != BACKSLASH)
						{
							if(escaped)
							{
								if(escapeChar == c)
								{
									escaped = false;
								}
							}
							else
							{
								escaped = true;
								escapeChar = c;
							}
						}
						break;
					}
				}
				if(!escaped)
				{
					switch(c)
					{
						case LEFT_BRACE:
						{
							if(tabs > 0 && p != LEFT_BRACE && p != LEFT_BRACKET && p != COMMA)
							{
								builder.append(NEW_LINE);
								builder.append(tabs(tabs));
							}
							builder.append(c);
							builder.append(NEW_LINE);
							builder.append(tabs(++tabs));
							break;
						}
						case LEFT_BRACKET:
						{
							if(p != LEFT_BRACE && p != LEFT_BRACKET && p != COMMA)
							{
								builder.append(NEW_LINE);
								builder.append(tabs(tabs));
							}
							builder.append(c);
							builder.append(NEW_LINE);
							builder.append(tabs(++tabs));
							break;
						}
						case RIGHT_BRACE:
						{
							builder.append(NEW_LINE);
							builder.append(tabs(--tabs));
							builder.append(c);
							break;
						}
						case RIGHT_BRACKET:
						{
							builder.append(NEW_LINE);
							builder.append(tabs(--tabs));
							builder.append(c);
							break;
						}
						case COMMA:
						{
							builder.append(c);
							builder.append(NEW_LINE);
							builder.append(tabs(tabs));
							break;
						}
						default:
						{
							builder.append(c);
							break;
						}
					}
				}
				else
				{
					builder.append(c);
				}
				p = c;
			}
			builder.append(NEW_LINE);
		}
		catch(IOException e)
		{
			
		}
		return builder.toString();
	}
	
}
