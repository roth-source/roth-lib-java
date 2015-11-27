package roth.lib.java.map.xml;

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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.java.map.Mapper;
import roth.lib.java.map.MapperConfig;
import roth.lib.java.map.MapperReflector;
import roth.lib.java.map.deserializer.Deserializer;
import roth.lib.java.map.serializer.Serializer;
import roth.lib.java.map.xml.tag.CloseTag;
import roth.lib.java.map.xml.tag.CommentTag;
import roth.lib.java.map.xml.tag.DeclarationTag;
import roth.lib.java.map.xml.tag.EmptyTag;
import roth.lib.java.map.xml.tag.OpenTag;
import roth.lib.java.map.xml.tag.Tag;
import roth.lib.java.reflector.PropertyReflector;

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
		this(XmlReflector.get());
	}
	
	public XmlMapper(MapperConfig mapperConfig)
	{
		this(XmlReflector.get(), mapperConfig);
	}
	
	public XmlMapper(MapperReflector mapperReflector)
	{
		this(mapperReflector, null);
	}
	
	public XmlMapper(MapperReflector mapperReflector, MapperConfig mapperConfig)
	{
		super(mapperReflector, mapperConfig);
	}
	
	@Override
	public XmlReflector getMapperReflector()
	{
		return (XmlReflector) super.getMapperReflector();
	}
	
	@Override
	public void serialize(Object value, Writer writer)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		try
		{
			String rootName = getMapperReflector().getEntityName(value.getClass());
			if(rootName == null || rootName.isEmpty())
			{
				rootName = "root";
			}
			writer.write(XML_HEADER);
			writeNewLine(writer);
			writeOpenTag(writer, rootName, getMapperReflector().getXmlAttributeMap(value));
			writeEntity(writer, value);
			writeNewLine(writer);
			writeCloseTag(writer, rootName);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new XmlException(e);
		}
	}
	
	@Override
	public void serialize(Map<String, ?> map, Writer writer)
	{
		serialize(map, "root", writer);
	}
	
	public void serialize(Map<String, ?> map, String rootName, Writer writer)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		try
		{
			writer.write(XML_HEADER);
			writeNewLine(writer);
			writeOpenTag(writer, rootName);
			writeMap(writer, map);
			writeNewLine(writer);
			writeCloseTag(writer, rootName);
			writer.flush();
		}
		catch(IOException e)
		{
			throw new XmlException(e);
		}
	}
	
	protected void writeEntity(Writer writer, Object value) throws IOException
	{
		for(PropertyReflector propertyFieldAccessor : getMapperReflector().getPropertyReflectors(value.getClass()))
		{
			if(!hasContext() || !propertyFieldAccessor.getExcludes().contains(getContext()))
			{
				setTimeFormat(propertyFieldAccessor.getTimeFormat());
				Field field = propertyFieldAccessor.getField();
				String propertyName = propertyFieldAccessor.getPropertyName();
				Object fieldValue = getFieldValue(field, value);
				writeProperty(writer, propertyName, fieldValue, field);
			}
		}
	}
	
	protected void writeProperty(Writer writer, String name, Object value, Field field) throws IOException
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
				String xmlPropertyName = getMapperReflector().getXmlPropertyName(value.getClass());
				if(xmlPropertyName != null)
				{
					name = xmlPropertyName;
				}
				writeNewLine(writer);
				writeOpenTag(writer, name, getMapperReflector().getXmlAttributeMap(value));
				writeEntity(writer, value);
				writeNewLine(writer);
				writeCloseTag(writer, name);
			}
			else if(isArray(value.getClass()) || isCollection(value.getClass()) && field != null)
			{
				LinkedList<?> values = asCollection(value);
				String xmlElementsName = getMapperReflector().getXmlElementsName(field);
				if(xmlElementsName != null)
				{
					writeNewLine(writer);
					writeOpenTag(writer, name);
					if(!values.isEmpty())
					{
						writeArray(writer, xmlElementsName, values);
						writeNewLine(writer);
					}
					writeCloseTag(writer, name);
				}
				else
				{
					if(!values.isEmpty())
					{
						writeArray(writer, name, values);
					}
				}
			}
			else if(isMap(value.getClass()))
			{
				LinkedHashMap<?, ?> valueMap = asMap(value);
				writeNewLine(writer);
				writeOpenTag(writer, name);
				if(!valueMap.isEmpty())
				{
					writeMap(writer, valueMap);
					writeNewLine(writer);
				}
				writeCloseTag(writer, name);
			}
			else if(getMapperConfig().isSerializable(value.getClass()))
			{
				Serializer<?> serializer = getMapperConfig().getSerializer(value.getClass());
				String serializedValue = serializer.serialize(value, getTimeFormat());
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
			decrementTabs();
		}
	}
	
	protected void writeOpenTag(Writer writer, String name) throws IOException
	{
		writeOpenTag(writer, name, null);
	}
	
	protected void writeOpenTag(Writer writer, String name, Map<String, String> attributeMap) throws IOException
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
		if(getMapperConfig().isPrettyPrinting())
		{
			writer.write(NEW_LINE);
			for(int i = 0; i < tabs; i++)
			{
				writer.write(TAB);
			}
		}
	}
	
	protected void writeArray(Writer writer, String name, Collection<?> values) throws IOException
	{
		for(Object value : values)
		{
			writeProperty(writer, name, value, null);
		}
	}
	
	protected void writeMap(Writer writer, Map<?, ?> valueMap) throws IOException
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
				Serializer<?> serializer = getMapperConfig().getSerializer(key.getClass());
				if(serializer != null)
				{
					name = serializer.serialize(key, getTimeFormat());
				}
			}
			if(name != null)
			{
				writeProperty(writer, name, valueEntry.getValue(), null);
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
				if(tag instanceof DeclarationTag)
				{
					
				}
				else if(tag instanceof CommentTag)
				{
					
				}
				else if(tag instanceof CloseTag)
				{
					break;
				}
				else if(tag instanceof OpenTag)
				{
					model = readEntity(reader, (OpenTag) tag, type);
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
	public LinkedHashMap<String, Object> deserialize(Reader reader)
	{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		try
		{
			readUntil(reader, LEFT_ANGLE_BRACKET);
			Tag tag = null;
			while((tag = readTag(reader)) != null)
			{
				if(tag instanceof DeclarationTag)
				{
					
				}
				else if(tag instanceof CommentTag)
				{
					
				}
				else if(tag instanceof CloseTag)
				{
					break;
				}
				else if(tag instanceof OpenTag)
				{
					map = readMap(reader, String.class);
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
		LinkedHashMap<String, PropertyReflector> namePropertyReflectorMap = getMapperReflector().getNamePropertyReflectorMap(type, true);
		Class<T> klass = getTypeClass(type);
		Constructor<T> constructor = klass.getDeclaredConstructor();
		constructor.setAccessible(true);
		T model = constructor.newInstance();
		getMapperReflector().setAttributes(model, openTag.getAttributeMap());
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader)) != null)
		{
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			else if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				String name = getMapperReflector().getFlexibleName(fieldOpenTag.getName());
				PropertyReflector propertyReflector = namePropertyReflectorMap.get(name);
				if(propertyReflector != null)
				{
					setTimeFormat(propertyReflector.getTimeFormat());
					Field field = propertyReflector.getField();
					Type fieldType = propertyReflector.getFieldType();
					Class<?> fieldClass = propertyReflector.getFieldClass();
					if(getMapperReflector().isEntity(fieldType))
					{
						Object value = readEntity(reader, fieldOpenTag, fieldType);
						field.set(model, value);
						setDeserializedName(model, propertyReflector.getFieldName());
					}
					else if(isCollection(fieldType))
					{
						String xmlElementsName = getMapperReflector().getXmlElementsName(field);
						if(xmlElementsName != null)
						{
							Object value = readCollection(reader, fieldType);
							field.set(model, value);
							setDeserializedName(model, propertyReflector.getFieldName());
						}
						else
						{
							Object value = readCollectionElement(reader, fieldOpenTag, fieldType);
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
						Object value = readArray(reader, fieldType);
						field.set(model, value);
						setDeserializedName(model, propertyReflector.getFieldName());
					}
					else if(isMap(fieldType))
					{
						Object value = readMap(reader, fieldType);
						field.set(model, value);
						setDeserializedName(model, propertyReflector.getFieldName());
					}
					else if(getMapperConfig().isSerializable(getTypeClass(fieldType)))
					{
						String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
						readTag(reader);
						Deserializer<?> deserializer = getMapperConfig().getDeserializer(fieldClass);
						if(deserializer != null)
						{
							field.set(model, deserializer.deserialize(value, getTimeFormat(), fieldClass));
							setDeserializedName(model, propertyReflector.getFieldName());
						}
					}
					else
					{
						readUnmapped(reader, fieldOpenTag);
					}
				}
				else
				{
					readUnmapped(reader, fieldOpenTag);
				}
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return model;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, K, E> T readMap(Reader reader, Type type) throws Exception
	{
		Map<K, E> map = null;
		Class<T> klass = getTypeClass(type);
		Type keyType = getKeyType(type);
		Class<K> keyClass = getTypeClass(keyType);
		Type elementType = getElementType(type);
		Class<E> elementClass = getTypeClass(elementType);
		if(klass.isAssignableFrom(LinkedHashMap.class))
		{
			map = new LinkedHashMap<K, E>();
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
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			else if(tag instanceof OpenTag)
			{
				OpenTag fieldOpenTag = (OpenTag) tag;
				K key = null;
				Deserializer<?> keyDeserializer = getMapperConfig().getDeserializer(keyClass);
				if(keyDeserializer != null)
				{
					key = (K) keyDeserializer.deserialize(fieldOpenTag.getName(), getTimeFormat());
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
					E value = readCollection(reader, elementType);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else if(isArray(elementType))
				{
					E value = readArray(reader, elementType);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else if(isMap(elementType))
				{
					E value = readMap(reader, elementType);
					if(value != null)
					{
						map.put(key, value);
					}
				}
				else if(getMapperConfig().isSerializable(elementClass))
				{
					String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
					readTag(reader);
					Deserializer<E> deserializer = (Deserializer<E>) getMapperConfig().getDeserializer(elementClass);
					if(deserializer != null)
					{
						E deserializedValue = deserializer.deserialize(value, getTimeFormat(), elementClass);
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
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return (T) map;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readArray(Reader reader, Type type) throws Exception
	{
		Type elementType = getElementType(type);
		Class<E> elementClass = getTypeClass(elementType);
		LinkedList<E> collection = readCollection(reader, type);
		E[] array = (E[]) Array.newInstance(elementClass, collection.size());
		for(int i = 0; i < collection.size(); i++)
		{
			array[i] = collection.get(i);
		}
		return (T) array;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> T readCollection(Reader reader, Type type) throws Exception
	{
		Collection<E> collection = null;
		Class<T> klass = getTypeClass(type);
		Type elementType = getElementType(type);
		Class<E> elementClass = getTypeClass(elementType);
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
		readUntil(reader, LEFT_ANGLE_BRACKET);
		Tag tag = null;
		while((tag = readTag(reader)) != null)
		{
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof CloseTag)
			{
				break;
			}
			else if(tag instanceof OpenTag)
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
					E value = readCollection(reader, elementType);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isArray(elementType))
				{
					E value = readArray(reader, elementType);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(isMap(elementType))
				{
					E value = readMap(reader, elementType);
					if(value != null)
					{
						collection.add(value);
					}
				}
				else if(getMapperConfig().isSerializable(elementClass))
				{
					String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
					readTag(reader);
					Deserializer<E> deserializer = (Deserializer<E>) getMapperConfig().getDeserializer(elementClass);
					if(deserializer != null)
					{
						E deserializedValue = deserializer.deserialize(value, getTimeFormat(), elementClass);
						if(deserializedValue != null)
						{
							collection.add(deserializedValue);
						}
					}
				}
				else
				{
					readUnmapped(reader, fieldOpenTag);
				}
			}
			readUntil(reader, LEFT_ANGLE_BRACKET);
		}
		return (T) collection;
	}
	
	@SuppressWarnings("unchecked")
	protected <T, E> E readCollectionElement(Reader reader, OpenTag tag, Type type) throws Exception
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
			element = readCollection(reader, elementType);
		}
		else if(isArray(elementType))
		{
			element = readArray(reader, elementType);
		}
		else if(isMap(elementType))
		{
			element = readMap(reader, elementType);
		}
		else if(getMapperConfig().isSerializable(elementClass))
		{
			String value = readEscaped(reader, LEFT_ANGLE_BRACKET);
			readTag(reader);
			Deserializer<E> deserializer = (Deserializer<E>) getMapperConfig().getDeserializer(elementClass);
			if(deserializer != null)
			{
				element = deserializer.deserialize(value, getTimeFormat(), elementClass);
			}
		}
		else
		{
			readUnmapped(reader, tag);
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
			if(tag instanceof DeclarationTag)
			{
				
			}
			else if(tag instanceof CommentTag)
			{
				
			}
			else if(tag instanceof EmptyTag)
			{
				
			}
			else if(tag instanceof OpenTag)
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
