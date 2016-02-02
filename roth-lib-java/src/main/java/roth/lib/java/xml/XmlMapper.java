package roth.lib.java.xml;

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
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map.Entry;

import roth.lib.java.deserializer.Deserializer;
import roth.lib.java.lang.List;
import roth.lib.java.lang.Map;
import roth.lib.java.mapper.Mapper;
import roth.lib.java.mapper.MapperConfig;
import roth.lib.java.mapper.MapperType;
import roth.lib.java.reflector.EntityReflector;
import roth.lib.java.reflector.MapperReflector;
import roth.lib.java.reflector.PropertyReflector;
import roth.lib.java.serializer.Serializer;
import roth.lib.java.xml.tag.CloseTag;
import roth.lib.java.xml.tag.CommentTag;
import roth.lib.java.xml.tag.DeclarationTag;
import roth.lib.java.xml.tag.EmptyTag;
import roth.lib.java.xml.tag.OpenTag;
import roth.lib.java.xml.tag.Tag;

public class XmlMapper extends Mapper
{
	protected static final String XML_HEADER					= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	protected static final String ESCAPED_QUOTE					= "quot";
	protected static final String ESCAPED_SINGLE_QUOTE			= "apos";
	protected static final String ESCAPED_LEFT_ANGLE_BRACKET	= "lt";
	protected static final String ESCAPED_RIGHT_ANGLE_BRACKET	= "gt";
	protected static final String ESCAPED_AMPERSAND				= "amp";
	
	public XmlMapper()
	{
		this(MapperReflector.get());
	}
	
	public XmlMapper(MapperConfig mapperConfig)
	{
		this(MapperReflector.get(), mapperConfig);
	}
	
	public XmlMapper(MapperReflector mapperReflector)
	{
		this(mapperReflector, MapperConfig.get());
	}
	
	public XmlMapper(MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		super(MapperType.XML, mapperReflector, mapperConfig);
	}
	
	@Override
	public void serialize(Object value, Writer writer)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		try
		{
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
			if(entityReflector != null)
			{
				String rootName = entityReflector.getEntityName();
				if(rootName == null || rootName.isEmpty())
				{
					rootName = "root";
				}
				writer.write(XML_HEADER);
				writeNewLine(writer);
				writeOpenTag(writer, rootName, entityReflector.getAttributeMap(value, getMapperType()));
				writeEntity(writer, value, entityReflector);
				writeNewLine(writer);
				writeCloseTag(writer, rootName);
				writer.flush();
			}
			else
			{
				throw new XmlException("Value is not an entity");
			}
		}
		catch(IOException e)
		{
			throw new XmlException(e);
		}
	}
	
	@Override
	public void serialize(java.util.Map<String, ?> map, Writer writer)
	{
		serialize(map, "root", writer);
	}
	
	public void serialize(java.util.Map<String, ?> map, String rootName, Writer writer)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		try
		{
			writer.write(XML_HEADER);
			writeNewLine(writer);
			writeOpenTag(writer, rootName);
			writeMap(writer, map, null);
			writeNewLine(writer);
			writeCloseTag(writer, rootName);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new XmlException(e);
		}
	}
	
	protected void writeEntity(Writer writer, Object value, EntityReflector entityReflector) throws IOException
	{
		for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
		{
			if(!hasContext() || !propertyReflector.isExcluded(getContext()))
			{
				String propertyName = propertyReflector.getPropertyName(getMapperType());
				Object propertyValue = getFieldValue(propertyReflector.getField(), value);
				writeProperty(writer, propertyName, propertyValue, propertyReflector);
			}
		}
	}
	
	protected void writeProperty(Writer writer, String name, Object value, PropertyReflector propertyReflector) throws IOException
	{
		if(name != null && (value != null || getMapperConfig().isSerializeNulls()))
		{
			incrementTabs();
			if(value == null)
			{
				writeNewLine(writer);
				writeOpenTag(writer, name);
				writer.write(BLANK);
				writeCloseTag(writer, name);
			}
			else if(getMapperReflector().isEntity(value.getClass()))
			{
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
				String propertyName = entityReflector.getPropertyName();
				if(propertyName != null)
				{
					name = propertyName;
				}
				writeNewLine(writer);
				writeOpenTag(writer, name, entityReflector.getAttributeMap(value, getMapperType()));
				writeEntity(writer, value, entityReflector);
				writeNewLine(writer);
				writeCloseTag(writer, name);
			}
			else if(isArray(value.getClass()) || isCollection(value.getClass()))
			{
				List<?> values = asCollection(value);
				String elementsName = propertyReflector != null ? propertyReflector.getElementsName() : null;
				if(elementsName != null)
				{
					writeNewLine(writer);
					writeOpenTag(writer, name);
					if(!values.isEmpty())
					{
						writeArray(writer, elementsName, values, propertyReflector);
						writeNewLine(writer);
					}
					writeCloseTag(writer, name);
				}
				else if(!values.isEmpty())
				{
					writeArray(writer, name, values, propertyReflector);
				}
			}
			else if(isMap(value.getClass()))
			{
				Map<?, ?> valueMap = asMap(value);
				writeNewLine(writer);
				writeOpenTag(writer, name);
				if(!valueMap.isEmpty())
				{
					writeMap(writer, valueMap, propertyReflector);
					writeNewLine(writer);
				}
				writeCloseTag(writer, name);
			}
			else
			{
				Serializer<?> serializer = getSerializer(value.getClass());
				if(serializer != null)
				{
					String timeFormat = getTimeFormat(propertyReflector);
					String serializedValue = serializer.serialize(value, timeFormat);
					if(serializedValue != null)
					{
						writeNewLine(writer);
						writeOpenTag(writer, name);
						writeValue(writer, serializedValue);
						writeCloseTag(writer, name);
					}
					else if(getMapperConfig().isSerializeNulls())
					{
						writeNewLine(writer);
						writeOpenTag(writer, name);
						writer.write(BLANK);
						writeCloseTag(writer, name);
					}
				}
			}
			decrementTabs();
		}
	}
	
	protected void writeOpenTag(Writer writer, String name) throws IOException
	{
		writeOpenTag(writer, name, null);
	}
	
	protected void writeOpenTag(Writer writer, String name, java.util.Map<String, String> attributeMap) throws IOException
	{
		writer.write(LEFT_ANGLE_BRACKET);
		writer.write(name);
		if(attributeMap != null)
		{
			for(Entry<String, String> attributeEntry : attributeMap.entrySet())
			{
				writer.write(SPACE);
				writer.write(attributeEntry.getKey());
				writer.write(EQUAL);
				writer.write(QUOTE);
				writeValue(writer, attributeEntry.getValue());
				writer.write(QUOTE);
			}
		}
		writer.write(RIGHT_ANGLE_BRACKET);
	}
	
	protected void writeCloseTag(Writer writer, String name) throws IOException
	{
		writer.write(LEFT_ANGLE_BRACKET);
		writer.write(SLASH);
		writer.write(name);
		writer.write(RIGHT_ANGLE_BRACKET);
	}
	
	protected void writeNewLine(Writer writer) throws IOException
	{
		if(isPrettyPrint())
		{
			writer.write(NEW_LINE);
			for(int i = 0; i < tabs; i++)
			{
				writer.write(TAB);
			}
		}
	}
	
	protected void writeArray(Writer writer, String name, Collection<?> values, PropertyReflector propertyReflector) throws IOException
	{
		for(Object value : values)
		{
			writeProperty(writer, name, value, propertyReflector);
		}
	}
	
	protected void writeMap(Writer writer, java.util.Map<?, ?> valueMap, PropertyReflector propertyReflector) throws IOException
	{
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
				writeProperty(writer, name, valueEntry.getValue(), propertyReflector);
			}
		}
	}
	
	protected void writeValue(Writer writer, String value) throws IOException
	{
		if(value != null)
		{
			StringReader reader = new StringReader(value);
			int b;
			char c;
			while((b = reader.read()) > -1)
			{
				c = (char) b;
				switch(c)
				{
					case QUOTE:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_QUOTE);
						writer.write(SEMI_COLON);
						break;
					}
					case SINGLE_QUOTE:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_SINGLE_QUOTE);
						writer.write(SEMI_COLON);
						break;
					}
					case LEFT_ANGLE_BRACKET:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_LEFT_ANGLE_BRACKET);
						writer.write(SEMI_COLON);
						break;
					}
					case RIGHT_ANGLE_BRACKET:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_RIGHT_ANGLE_BRACKET);
						writer.write(SEMI_COLON);
						break;
					}
					case AMPERSAND:
					{
						writer.write(AMPERSAND);
						writer.write(ESCAPED_AMPERSAND);
						writer.write(SEMI_COLON);
						break;
					}
					default:
					{
						writer.write(c);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type)
	{
		T model = null;
		try
		{
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			while((tag = readTag(reader)) != null)
			{
				if(tag instanceof OpenTag)
				{
					model = readEntity(reader, (OpenTag) tag, type);
					break;
				}
				else if(tag instanceof EmptyTag)
				{
					EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
					Class<T> klass = getTypeClass(type);
					Constructor<T> constructor = klass.getDeclaredConstructor();
					constructor.setAccessible(true);
					model = constructor.newInstance();
					entityReflector.setAttributeMap(model, getMapperType(), ((EmptyTag) tag).getAttributeMap());
					break;
				}
				else if(tag instanceof CloseTag)
				{
					break;
				}
				readUntil(reader, LEFT_ANGLE_BRACKET);
			}
		}
		catch(Exception e)
		{
			throw new XmlException(e);
		}
		return model;
	}
	
	@Override
	public Map<String, Object> deserialize(Reader reader)
	{
		Map<String, Object> map = new Map<String, Object>();
		try
		{
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			while((tag = readTag(reader)) != null)
			{
				if(tag instanceof OpenTag)
				{
					map = readMap(reader, String.class, null);
					break;
				}
				else if(tag instanceof CloseTag)
				{
					break;
				}
				readUntil(reader, LEFT_ANGLE_BRACKET);
			}
		}
		catch(Exception e)
		{
			throw new XmlException(e);
		}
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T readEntity(Reader reader, OpenTag openTag, Type type) throws Exception
	{
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
		Class<T> klass = getTypeClass(type);
		Constructor<T> constructor = klass.getDeclaredConstructor();
		constructor.setAccessible(true);
		T model = constructor.newInstance();
		entityReflector.setAttributeMap(model, getMapperType(), openTag.getAttributeMap());
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader)) != null)
		{
			if(tag instanceof OpenTag)
			{
				OpenTag propertyOpenTag = (OpenTag) tag;
				PropertyReflector propertyReflector = entityReflector.getPropertyReflector(propertyOpenTag.getName(), getMapperType(), getMapperReflector());
				if(propertyReflector != null)
				{
					Field field = propertyReflector.getField();
					Type fieldType = propertyReflector.getFieldType();
					Class<?> fieldClass = propertyReflector.getFieldClass();
					if(getMapperReflector().isEntity(fieldType))
					{
						Object value = readEntity(reader, propertyOpenTag, fieldType);
						field.set(model, value);
						setDeserializedName(model, propertyReflector.getFieldName());
					}
					else if(isCollection(fieldType))
					{
						String elementsName = propertyReflector.getElementsName();
						if(elementsName != null)
						{
							Object value = readCollection(reader, fieldType, propertyReflector);
							field.set(model, value);
							setDeserializedName(model, propertyReflector.getFieldName());
						}
						else
						{
							Object value = readCollectionElement(reader, propertyOpenTag, fieldType, propertyReflector);
							if(value != null)
							{
								Collection collection = (Collection) field.get(model);
								if(collection == null)
								{
									Constructor<?> fieldConstructor = getTypeClass(fieldType).getDeclaredConstructor();
									fieldConstructor.setAccessible(true);
									collection = (Collection) fieldConstructor.newInstance();
									field.set(model, collection);
									setDeserializedName(model, propertyReflector.getFieldName());
								}
								collection.add(value);
							}
						}
					}
					else if(isArray(fieldType))
					{
						Object value = readArray(reader, fieldType, propertyReflector);
						field.set(model, value);
						setDeserializedName(model, propertyReflector.getFieldName());
					}
					else if(isMap(fieldType))
					{
						Object value = readMap(reader, fieldType, propertyReflector);
						field.set(model, value);
						setDeserializedName(model, propertyReflector.getFieldName());
					}
					else
					{
						Deserializer<?> deserializer = getDeserializer(fieldClass);
						if(deserializer != null)
						{
							String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
							readTag(reader);
							if(deserializer != null)
							{
								String timeFormat = getTimeFormat(propertyReflector);
								field.set(model, deserializer.deserialize(value, timeFormat, fieldClass));
								setDeserializedName(model, propertyReflector.getFieldName());
							}
						}
						else
						{
							readUnmapped(reader, propertyOpenTag);
						}
					}
				}
				else
				{
					readUnmapped(reader, propertyOpenTag);
				}
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
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
		Class<E> elementClass = getTypeClass(elementType);
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
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader)) != null)
		{
			if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				K key = null;
				Deserializer<?> keyDeserializer = getDeserializer(keyClass);
				if(keyDeserializer != null)
				{
					String timeFormat = getTimeFormat(propertyReflector);
					key = (K) keyDeserializer.deserialize(fieldOpenTag.getName(), timeFormat);
				}
				if(getMapperReflector().isEntity(elementType))
				{
					E value = readEntity(reader, (OpenTag) tag, elementType);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else if(isCollection(elementType))
				{
					E value = readCollection(reader, elementType, propertyReflector);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else if(isArray(elementType))
				{
					E value = readArray(reader, elementType, propertyReflector);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else if(isMap(elementType))
				{
					E value = readMap(reader, elementType, propertyReflector);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else
				{
					Deserializer<E> deserializer = (Deserializer<E>) getDeserializer(elementClass);
					if(deserializer != null)
					{
						String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
						readTag(reader);
						if(deserializer != null)
						{
							String timeFormat = getTimeFormat(propertyReflector);
							E deserializedValue = deserializer.deserialize(value, timeFormat, elementClass);
							if(deserializedValue != null)
							{
								map.put(key, deserializedValue);
							}
						}
					}
					else
					{
						readUnmapped(reader, fieldOpenTag);
					}
				}
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return (T) map;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readArray(Reader reader, Type type, PropertyReflector propertyReflector) throws Exception
	{
		Type elementType = getElementType(type);
		Class<E> elementClass = getTypeClass(elementType);
		List<E> collection = readCollection(reader, type, propertyReflector);
		E[] array = (E[]) Array.newInstance(elementClass, collection.size());
		for(int i = 0; i < collection.size(); i++)
		{
			array[i] = collection.get(i);
		}
		return (T) array;
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
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader)) != null)
		{
			if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				if(getMapperReflector().isEntity(elementType))
				{
					E value = readEntity(reader, fieldOpenTag, elementType);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isCollection(elementType))
				{
					E value = readCollection(reader, elementType, propertyReflector);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isArray(elementType))
				{
					E value = readArray(reader, elementType, propertyReflector);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isMap(elementType))
				{
					E value = readMap(reader, elementType, propertyReflector);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else
				{
					Deserializer<E> deserializer = (Deserializer<E>) getDeserializer(elementClass);
					if(deserializer != null)
					{
						String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
						readTag(reader);
						String timeFormat = getTimeFormat(propertyReflector);
						E deserializedValue = deserializer.deserialize(value, timeFormat, elementClass);
						if(deserializedValue != null)
						{
							collection.add(deserializedValue);
						}
					}
					else
					{
						readUnmapped(reader, fieldOpenTag);
					}
				}
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return (T) collection;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> E readCollectionElement(Reader reader, OpenTag tag, Type type, PropertyReflector propertyReflector) throws Exception
	{
		E element = null;
		Type elementType = getElementType(type);
		Class<E> elementClass = getTypeClass(elementType);
		if(getMapperReflector().isEntity(elementType))
		{
			element = readEntity(reader, tag, elementType);
		}
		else if(isCollection(elementType))
		{
			element = readCollection(reader, elementType, propertyReflector);
		}
		else if(isArray(elementType))
		{
			element = readArray(reader, elementType, propertyReflector);
		}
		else if(isMap(elementType))
		{
			element = readMap(reader, elementType, propertyReflector);
		}
		else
		{
			Deserializer<E> deserializer = (Deserializer<E>) getDeserializer(elementClass);
			if(deserializer != null)
			{
				String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
				readTag(reader);
				String timeFormat = getTimeFormat(propertyReflector);
				element = deserializer.deserialize(value, timeFormat, elementClass);
			}
			else
			{
				readUnmapped(reader, tag);
			}
		}
		return element;
	}
	
	protected Tag readTag(Reader reader) throws IOException
	{
		Tag tag = null;
		StringBuilder builder = null;
		int b;
		char c;
		char p = 0;
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			if(builder == null)
			{
				switch(c)
				{
					case QUESTION:
					{
						String value = readUntil(reader, "?>");
						tag = new DeclarationTag(value.replaceAll("\\?>$", "").trim());
						break read;
					}
					case EXCLAMATION:
					{
						String value = readUntil(reader, "-->");
						tag = new CommentTag(value.replaceAll("^--", "").replaceAll("-->$", "").trim());
						break read;
					}
					case SLASH:
					{
						String value = readUntil(reader, RIGHT_ANGLE_BRACKET);
						tag = new CloseTag(value.trim());
						break read;
					}
				}
				builder = new StringBuilder();
			}
			switch(c)
			{
				case TAB:
				case NEW_LINE:
				case CARRIAGE_RETURN:
				case FORM_FEED:
				case BACKSPACE:
				{
					break;
				}
				case SPACE:
				{
					String name = builder.toString();
					if(tag == null && !name.isEmpty())
					{
						tag = new OpenTag(name);
						builder.setLength(0);
					}
					break;
				}
				case EQUAL:
				{
					String name = builder.toString();
					if(!name.isEmpty())
					{
						String value = null;
						c = (char) reader.read();
						switch(c)
						{
							case QUOTE:
							{
								value = readEscaped(reader, QUOTE);
								break;
							}
							case SINGLE_QUOTE:
							{
								value = readEscaped(reader, SINGLE_QUOTE);
								break;
							}
						}
						if(tag != null && value != null && tag instanceof OpenTag)
						{
							((OpenTag) tag).addAttribute(name, value);
						}
						builder.setLength(0);
					}
					break;
				}
				case RIGHT_ANGLE_BRACKET:
				{
					if(tag == null)
					{
						String name = builder.toString();
						if(!name.isEmpty())
						{
							tag = new OpenTag(name);
							builder.setLength(0);
						}
					}
					if(p == SLASH && tag instanceof OpenTag)
					{
						tag = new EmptyTag((OpenTag) tag);
					}
					break read;
				}
				default:
				{
					builder.append(c);
					break;
				}
			}
			p = c;
		}
		return tag;
	}
	
	protected String readEscaped(Reader reader, final char until) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		int b;
		char c;
		read: while((b = reader.read()) > -1)
		{
			c = (char) b;
			switch(c)
			{
				case AMPERSAND:
				{
					c = (char) reader.read();
					switch(c)
					{
						case HASH:
						{
							String code = readUntil(reader, SEMI_COLON);
							try
							{
								builder.append((char) Integer.parseInt(code, 16));
							}
							catch(Exception e)
							{
								
							}
							break;
						}
						default:
						{
							String code = c + readUntil(reader, SEMI_COLON);
							switch(code)
							{
								case ESCAPED_QUOTE:
								{
									builder.append(QUOTE);
									break;
								}
								case ESCAPED_SINGLE_QUOTE:
								{
									builder.append(SINGLE_QUOTE);
									break;
								}
								case ESCAPED_LEFT_ANGLE_BRACKET:
								{
									builder.append(LEFT_ANGLE_BRACKET);
									break;
								}
								case ESCAPED_RIGHT_ANGLE_BRACKET:
								{
									builder.append(RIGHT_ANGLE_BRACKET);
									break;
								}
								case ESCAPED_AMPERSAND:
								{
									builder.append(AMPERSAND);
									break;
								}
							}
							break;
						}
					}
					break;
				}
				default:
				{
					if(c == until)
					{
						break read;
					}
					else
					{
						builder.append(c);
						break;
					}
				}
			}
		}
		return builder.toString();
	}
	
	protected void readUnmapped(Reader reader, OpenTag openTag) throws IOException
	{
		int nested = 0;
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader)) != null)
		{
			if(tag instanceof OpenTag)
			{
				nested++;
			}
			else if(tag instanceof CloseTag)
			{
				if(nested <= 0)
				{
					break;
				}
				nested--;
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
	}
	
	@Override
	public String prettyPrint(Reader reader)
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			int tabs = 0;
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			Tag lastTag = null;
			while((tag = readTag(reader)) != null)
			{
				if(lastTag == null)
				{
					builder.append(tag);
				}
				else if(tag instanceof OpenTag)
				{
					if(lastTag instanceof OpenTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(++tabs));
					}
					else if(lastTag instanceof EmptyTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CloseTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof DeclarationTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CommentTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					builder.append(tag);
				}
				else if(tag instanceof EmptyTag)
				{
					if(lastTag instanceof OpenTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(++tabs));
					}
					else if(lastTag instanceof EmptyTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CloseTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof DeclarationTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CommentTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					builder.append(tag);
				}
				else if(tag instanceof CloseTag)
				{
					if(lastTag instanceof OpenTag)
					{
						
					}
					else if(lastTag instanceof EmptyTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(--tabs));
					}
					else if(lastTag instanceof CloseTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(--tabs));
					}
					else if(lastTag instanceof DeclarationTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					else if(lastTag instanceof CommentTag)
					{
						builder.append(NEW_LINE);
						builder.append(tabs(tabs));
					}
					builder.append(tag);
				}
				else if(tag instanceof DeclarationTag)
				{
					builder.append(NEW_LINE);
					builder.append(tabs(tabs));
					builder.append(tag);
				}
				else if(tag instanceof CommentTag)
				{
					builder.append(NEW_LINE);
					builder.append(tabs(tabs));
					builder.append(tag);
				}
				String value = readUntil(reader, LEFT_ANGLE_BRACKET);
				if(value != null)
				{
					builder.append(value.trim());
				}
				lastTag = tag;
			}
		}
		catch(IOException e)
		{
			
		}
		builder.append(NEW_LINE);
		return builder.toString();
	}
	
}
