package roth.lib.map.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.annotation.Property;
import roth.lib.map.Deserializer;
import roth.lib.map.PropertyField;
import roth.lib.map.SerialMapper;
import roth.lib.map.Serializer;
import roth.lib.map.mapper.PropertyMapper;
import roth.lib.map.util.MapperUtil;

public class JsonMapper extends SerialMapper<JsonConfig>
{
	protected static String NULL 	= "null";
	protected static String TRUE 	= "true";
	protected static String FALSE	= "false";
	
	protected static JsonMapper instance;
	
	public JsonMapper()
	{
		super();
		propertyMappers.add(new PropertyMapper<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Field field, Property property)
			{
				if(property != null && property.json())
				{
					if(isValid(property.jsonName()))
					{
						return property.jsonName();
					}
					else if(isValid(property.name()))
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
	
	public static JsonMapper get()
	{
		if(instance == null)
		{
			instance = new JsonMapper();
		}
		return instance;
	}
	
	public static void set(JsonMapper newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public JsonConfig defaultConfig()
	{
		return JsonConfig.get();
	}
	
	@Override
	public void serialize(Object object, Writer writer, JsonConfig config)
	{
		if(object == null) throw new IllegalArgumentException("Object cannot be null");
		try
		{
			if(isArray(object.getClass()) || isCollection(object.getClass()))
			{
				LinkedList<?> values = asCollection(object);
				if(!values.isEmpty())
				{
					writeArray(writer, config, values, 0);
				}
				else
				{
					writer.write(LEFT_BRACKET);
					writer.write(RIGHT_BRACKET);
				}
			}
			else
			{
				writeEntity(writer, config, object, 0);
			}
			writer.flush();
		}
		catch(IOException e)
		{
			throw new JsonException(e);
		}
	}
	
	@Override
	public void serialize(Map<String, ?> map, Writer writer, JsonConfig config)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		try
		{
			writeMap(writer, config, map, 0);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new JsonException(e);
		}
	}
	
	protected void writeEntity(Writer writer, JsonConfig config, Object entity, final int tabs) throws IOException
	{
		writer.write(LEFT_BRACE);
		String seperator = BLANK;
		for(PropertyField propertyField : getPropertyFields(entity.getClass()))
		{
			String propertyName = propertyField.getPropertyName();
			Object propertyValue = getPropertyObject(propertyField.getField(), entity);
			seperator = writeProperty(writer, config, propertyName, propertyValue, seperator, tabs + 1);
		}
		writeNewLine(writer, config, tabs);
		writer.write(RIGHT_BRACE);
	}
	
	protected String writeProperty(Writer writer, JsonConfig config, String name, Object value, String seperator, final int tabs) throws IOException
	{
		if(name != null && (value != null || config.isSerializeNulls()))
		{
			if(value == null)
			{
				seperator = writeSeperator(writer, seperator);
				writeNewLine(writer, config, tabs);
				writePropertyName(writer, name);
				writer.write(NULL);
			}
			else if(isEntity(value.getClass()))
			{
				seperator = writeSeperator(writer, seperator);
				writeNewLine(writer, config, tabs);
				writePropertyName(writer, name);
				writeNewLine(writer, config, tabs);
				writeEntity(writer, config, value, tabs);
			}
			else if(isArray(value.getClass()) || isCollection(value.getClass()))
			{
				LinkedList<?> values = asCollection(value);
				if(!values.isEmpty())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs);
					writePropertyName(writer, name);
					writeArray(writer, config, values, tabs);
				}
				else if(config.isSerializeEmptyArray())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs);
					writePropertyName(writer, name);
					writer.write(LEFT_BRACKET);
					writer.write(RIGHT_BRACKET);
				}
			}
			else if(isMap(value.getClass()))
			{
				LinkedHashMap<String, ?> valueMap = asMap(value);
				if(!valueMap.isEmpty())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs);
					writePropertyName(writer, name);
					writeNewLine(writer, config, tabs);
					writeMap(writer, config, valueMap, tabs);
				}
				else if(config.isSerializeEmptyMap())
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs);
					writePropertyName(writer, name);
					writer.write(LEFT_BRACE);
					writer.write(RIGHT_BRACE);
				}
			}
			else if(isSerializable(value.getClass(), config))
			{
				seperator = writeSeperator(writer, seperator);
				writeNewLine(writer, config, tabs);
				Serializer<?> serializer = config.getSerializer(value.getClass());
				String serializedValue = serializer.serializeInternal(value);
				if(serializedValue != null)
				{
					writePropertyName(writer, name);
					writeValue(writer, config, serializedValue, serializer.isEscapable());
				}
				else if(config.isSerializeNulls())
				{
					writePropertyName(writer, name);
					writer.write(NULL);
				}
			}
		}
		return seperator;
	}
	
	protected void writePropertyName(Writer writer, String propertyName) throws IOException
	{
		writer.write(QUOTE);
		writer.write(propertyName);
		writer.write(QUOTE);
		writer.write(COLON);
	}
	
	protected void writeArray(Writer writer, JsonConfig config, Collection<?> values, final int tabs) throws IOException
	{
		if(tabs > 0)
		{
			writeNewLine(writer, config, tabs);
		}
		writer.write(LEFT_BRACKET);
		String seperator = BLANK;
		for(Object value : values)
		{
			if(value != null || config.isSerializeNulls())
			{
				if(value == null)
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs + 1);
					writer.write(NULL);
				}
				else if(isEntity(value.getClass()))
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs + 1);
					writeEntity(writer, config, value, tabs + 1);
				}
				else if(isArray(value.getClass()) || isCollection(value.getClass()))
				{
					LinkedList<?> arrayValues = asCollection(value);
					if(!arrayValues.isEmpty())
					{
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer, config, tabs);
						writeArray(writer, config, arrayValues, tabs);
					}
					else if(config.isSerializeEmptyArray())
					{
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer, config, tabs);
						writer.write(LEFT_BRACKET);
						writer.write(RIGHT_BRACKET);
					}
				}
				else if(isMap(value.getClass()))
				{
					LinkedHashMap<String, ?> valueMap = asMap(value);
					if(!valueMap.isEmpty())
					{
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer, config, tabs);
						writeMap(writer, config, valueMap, tabs);
					}
					else if(config.isSerializeEmptyMap())
					{
						seperator = writeSeperator(writer, seperator);
						writeNewLine(writer, config, tabs);
						writer.write(LEFT_BRACE);
						writer.write(RIGHT_BRACE);
					}
				}
				else if(isSerializable(value.getClass(), config))
				{
					seperator = writeSeperator(writer, seperator);
					writeNewLine(writer, config, tabs + 1);
					Serializer<?> serializer = config.getSerializer(value.getClass());
					String serializedValue = serializer.serializeInternal(value);
					if(serializedValue != null)
					{
						writeValue(writer, config, serializedValue, serializer.isEscapable());
					}
					else if(config.isSerializeNulls())
					{
						writer.write(NULL);
					}
				}
			}
		}
		writeNewLine(writer, config, tabs);
		writer.write(RIGHT_BRACKET);
	}
	
	protected void writeMap(Writer writer, JsonConfig config, Map<String, ?> valueMap, final int tabs) throws IOException
	{
		writer.write(LEFT_BRACE);
		String seperator = BLANK;
		for(Entry<String, ?> valueEntry : valueMap.entrySet())
		{
			seperator = writeProperty(writer, config, valueEntry.getKey(), valueEntry.getValue(), seperator, tabs + 1);
		}
		writeNewLine(writer, config, tabs);
		writer.write(RIGHT_BRACE);
	}
	
	protected void writeNewLine(Writer writer, JsonConfig config, int tabs) throws IOException
	{
		if(config.isPrettyPrinting())
		{
			writer.write(NEW_LINE);
			for(int i = 0; i < tabs; i++)
			{
				writer.write(TAB);
			}
		}
	}
	
	protected String writeSeperator(Writer writer, String seperator) throws IOException
	{
		writer.write(seperator);
		return getSeperator(seperator);
	}
	
	protected String getSeperator(String seperator)
	{
		return BLANK.equals(seperator) ? String.valueOf(COMMA) : seperator;
	}
	
	protected void writeValue(Writer writer, JsonConfig config, String value, boolean escapable) throws IOException
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
	public <T> T deserialize(Reader reader, Type type, JsonConfig config)
	{
		T model = null;
		try
		{
			if(isArray(type))
			{
				readUntil(reader, LEFT_BRACKET);
				model = readArray(reader, type, config);
			}
			else if(isCollection(type))
			{
				readUntil(reader, LEFT_BRACKET);
				model = readCollection(reader, type, config);
			}
			else
			{
				readUntil(reader, LEFT_BRACE);
				model = readEntity(reader, type, config);
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
	public LinkedHashMap<String, Object> deserialize(Reader reader, JsonConfig config)
	{
		LinkedHashMap<String, Object> map;
		try
		{
			readUntil(reader, LEFT_BRACE);
			map = (LinkedHashMap<String, Object>) readEntity(reader, LinkedHashMap.class, config);
		}
		catch(Exception e)
		{
			throw new JsonException(e);
		}
		return map;
	}
	
	protected <T> T readEntity(Reader reader, Type type, JsonConfig config) throws Exception
	{
		LinkedHashMap<String, PropertyField> propertyNameFieldMap = getPropertyNameFieldMap(type);
		Class<T> klass = MapperUtil.getClass(type);
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
						PropertyField propertyField = propertyNameFieldMap.get(name);
						if(propertyField != null)
						{
							Field field = propertyField.getField();
							Class<?> fieldClass = propertyField.getFieldClass();
							Deserializer<?> deserializer = config.getDeserializer(fieldClass);
							if(deserializer != null)
							{
								field.set(model, deserializer.deserialize(value, fieldClass));
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
						PropertyField propertyField = propertyNameFieldMap.get(name);
						if(propertyField == null)
						{
							c = readUnmapped(reader, config);
							name = null;
							if(RIGHT_BRACE == c)
							{
								break read;
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
						PropertyField propertyField = propertyNameFieldMap.get(name);
						if(propertyField != null)
						{
							Field field = propertyField.getField();
							Class<?> fieldClass = propertyField.getFieldClass();
							if(!NULL.equalsIgnoreCase(value))
							{
								Deserializer<?> deserializer = config.getDeserializer(fieldClass);
								if(deserializer != null)
								{
									field.set(model, deserializer.deserialize(value, fieldClass));
								}
							}
							else
							{
								field.set(model, null);
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
						PropertyField propertyField = propertyNameFieldMap.get(name);
						if(propertyField != null)
						{
							Field field = propertyField.getField();
							Type fieldType = propertyField.getFieldType();
							if(isEntity(fieldType))
							{
								Object value = readEntity(reader, fieldType, config);
								field.set(model, value);
							}
							else
							{
								Object value = readMap(reader, fieldType, config);
								field.set(model, value);
							}
						}
						else
						{
							// exception
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
						PropertyField propertyField = propertyNameFieldMap.get(name);
						if(propertyField != null)
						{
							Field field = propertyField.getField();
							Type fieldType = propertyField.getFieldType();
							Object value = readArray(reader, fieldType, config);
							field.set(model, value);
						}
						else
						{
							// exception
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
	protected <T, E> T readMap(Reader reader, Type type, JsonConfig config) throws Exception
	{
		Map<String, E> map = null;
		Class<T> klass = MapperUtil.getClass(type);
		Class<E> elementClass = (Class<E>) getElementClass(type);
		if(klass.isAssignableFrom(LinkedHashMap.class))
		{
			map = new LinkedHashMap<String, E>();
		}
		else
		{
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			map = (Map<String, E>) constructor.newInstance();
		}
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
						map.put(name, (E) value);
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
							map.put(name, null);
						}
						else if(TRUE.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value))
						{
							map.put(name, (E) new Boolean(value));
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
							map.put(name, number);
						}
						builder.setLength(0);
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
						if(isEntity(elementClass))
						{
							E value = readEntity(reader, elementClass, config);
							map.put(name, value);
						}
						else
						{
							E value = readMap(reader, elementClass, config);
							map.put(name, value);
						}
						name = null;
					}
					builder.setLength(0);
					break;
				}
				case LEFT_BRACKET:
				{
					if(name != null)
					{
						LinkedList<Object> value = readArray(reader, LinkedList.class, config);
						map.put(name, (E) value);
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
	protected <T, E> T readArray(Reader reader, Type type, JsonConfig config) throws Exception
	{
		if(isCollection(type))
		{
			return readCollection(reader, type, config);
		}
		else if(isArray(type))
		{
			Class<E> elementClass = (Class<E>) getElementClass(type);
			LinkedList<E> collection = readCollection(reader, type, config);
			E[] array = (E[]) Array.newInstance(elementClass, collection.size());
			for(int i = 0; i < collection.size(); i++)
			{
				array[i] = collection.get(i);
			}
			return (T) array;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readCollection(Reader reader, Type type, JsonConfig config) throws Exception
	{
		Collection<E> collection = null;
		Class<T> klass = MapperUtil.getClass(type);
		Class<E> elementClass = (Class<E>) getElementClass(type);
		if(klass.isAssignableFrom(LinkedList.class) || isArray(klass))
		{
			collection = new LinkedList<E>();
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
					Deserializer<?> deserializer = config.getDeserializer(elementClass);
					if(deserializer != null)
					{
						Object value = deserializer.deserialize(readEscaped(reader, c), elementClass);
						if(value != null && elementClass.isAssignableFrom(value.getClass()))
						{
							collection.add((E) value);
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
					Deserializer<?> deserializer = config.getDeserializer(elementClass);
					if(deserializer != null)
					{
						String value = builder.toString();
						if(value != null && !value.trim().isEmpty() && !NULL.equalsIgnoreCase(value))
						{
							Object object = deserializer.deserialize(value, elementClass);
							if(object != null && elementClass.isAssignableFrom(object.getClass()))
							{
								collection.add((E) object);
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
					Object value = readEntity(reader, elementClass, config);
					if(value != null && elementClass.isAssignableFrom(value.getClass()))
					{
						collection.add((E) value);
					}
					builder.setLength(0);
					break;
				}
				case LEFT_BRACKET:
				{
					
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
	
	protected char readUnmapped(Reader reader, JsonConfig config) throws IOException
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
	public String format(Reader reader)
	{
		int tabs = 0;
		StringBuilder builder = new StringBuilder();
		try
		{
			int b;
			char c;
			char p = 0;
			while((b = reader.read()) > -1)
			{
				c = (char) b;
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
				p = c;
			}
			builder.append(NEW_LINE);
		}
		catch(IOException e)
		{
			
		}
		return builder.toString();
	}
	
	protected String tabs(int tabs)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < tabs; i++)
		{
			builder.append(TAB);
		}
		return builder.toString();
	}
	
}
