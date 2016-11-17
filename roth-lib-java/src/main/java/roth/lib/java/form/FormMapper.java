package roth.lib.java.form;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Generic;
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
import roth.lib.java.type.MimeType;
import roth.lib.java.util.IdUtil;
import roth.lib.java.util.IoUtil;
import roth.lib.java.util.ReflectionUtil;
import roth.lib.java.util.UrlUtil;

public class FormMapper extends Mapper implements FormBoundary
{
	protected static final String CONTENT_DISPOSITION = "Content-Disposition: form-data; name=\"%s\"";
	protected static final String FILENAME = "; filename=\"%s\"";
	protected static final String CONTENT_TYPE = "Content-Type: %s";
	protected static final String PREFIX = "--";
	protected static final String CRLF = "\r\n";
	protected static final Pattern NAME_PATTERN = Pattern.compile(";(?:\\s)name=\"(.+?)\"");
	protected static final Pattern FILENAME_PATTERN = Pattern.compile(";(?:\\s)filename=\"(.+?)\"");
	protected static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile("Content-Type:(?:\\s)(\\S+)");
	
	protected String boundary;
	
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
	public String getBoundary()
	{
		if(boundary == null)
		{
			boundary = IdUtil.random(10);
		}
		return boundary;
	}
	
	@Override
	public void setBoundary(String boundary)
	{
		this.boundary = boundary;
	}
	
	public Map<String, String> getParamMap(Object value)
	{
		Map<String, String> paramMap = new Map<>();
		EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
		for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
		{
			if(!hasContext() || !propertyReflector.isExcluded(getContext()))
			{
				Class<?> propertyClass = propertyReflector.getFieldClass();
				String propertyName = propertyReflector.getPropertyName(getMapperType());
				if(propertyName != null)
				{
					Serializer<?> serializer = getSerializer(propertyClass, propertyReflector);
					if(serializer != null)
					{
						String serializedValue = null;
						Object fieldValue = ReflectionUtil.getFieldValue(propertyReflector.getField(), value);
						if(fieldValue != null)
						{
							String timeFormat = getTimeFormat(propertyReflector);
							serializedValue = serializer.serialize(fieldValue, getTimeZone(propertyReflector), timeFormat);
						}
						else if(getMapperConfig().isSerializeNulls())
						{
							serializedValue = BLANK;
						}
						if(serializedValue != null)
						{
							paramMap.put(propertyName, serializedValue);
						}
					}
				}
			}
		}
		return paramMap;
	}
	
	public boolean isMultipart()
	{
		return MimeType.MULTIPART_FORM_DATA.equals(getContentType());
	}
	
	@Override
	public void serialize(Object value, OutputStream output)
	{
		if(value == null) throw new IllegalArgumentException("Value cannot be null");
		if(isMultipart())
		{
			try
			{
				DataOutputStream dataOutput = output instanceof DataOutputStream ? (DataOutputStream) output : new DataOutputStream(output);
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(value.getClass());
				for(PropertyReflector propertyReflector : entityReflector.getPropertyReflectors(getMapperType()))
				{
					if(!hasContext() || !propertyReflector.isExcluded(getContext()))
					{
						String propertyName = propertyReflector.getPropertyName(getMapperType());
						if(propertyName != null)
						{
							Object fieldValue = ReflectionUtil.getFieldValue(propertyReflector.getField(), value);
							if(fieldValue instanceof byte[])
							{
								writeFile(dataOutput, propertyName, propertyName, new ByteArrayInputStream((byte[]) fieldValue));
							}
							else if(fieldValue instanceof FormData)
							{
								FormData formData = (FormData) fieldValue;
								writeFile(dataOutput, propertyName, formData.getFilename(), formData.getInput(), formData.getContentType());
							}
							else if(fieldValue instanceof FormFile)
							{
								FormFile formFile = (FormFile) fieldValue;
								try(FileInputStream input = new FileInputStream(formFile.getFile()))
								{
									writeFile(dataOutput, propertyName, formFile.getFilename(), input, formFile.getContentType());
								}
							}
							else if(fieldValue != null && (ReflectionUtil.isArray(fieldValue.getClass()) || ReflectionUtil.isCollection(fieldValue.getClass())))
							{
								List<?> elementValues = ReflectionUtil.asCollection(fieldValue);
								for(Object elementValue : elementValues)
								{
									writeProperty(dataOutput, propertyName, elementValue, propertyReflector);
								}
							}
							else
							{
								writeProperty(dataOutput, propertyName, fieldValue, propertyReflector);
							}
						}
					}
				}
				dataOutput.writeChars(PREFIX);
				dataOutput.writeChars(getBoundary());
				dataOutput.writeChars(PREFIX);
				dataOutput.writeChar(CARRIAGE_RETURN);
				dataOutput.writeChar(NEW_LINE);
				output.flush();
			}
			catch(IOException e)
			{
				throw new FormException(e);
			}
		}
		else
		{
			super.serialize(value, output);
		}
	}
	
	@Override
	public void serialize(java.util.Map<String, ?> map, OutputStream output)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		if(isMultipart())
		{
			try
			{
				DataOutputStream dataOutput = output instanceof DataOutputStream ? (DataOutputStream) output : new DataOutputStream(output);
				for(Entry<String, ?> entry : map.entrySet())
				{
					String propertyName = entry.getKey();
					Object fieldValue = entry.getValue();
					if(fieldValue instanceof byte[])
					{
						writeFile(dataOutput, propertyName, propertyName, new ByteArrayInputStream((byte[]) fieldValue));
					}
					else if(fieldValue instanceof FormData)
					{
						FormData formData = (FormData) fieldValue;
						writeFile(dataOutput, propertyName, formData.getFilename(), formData.getInput(), formData.getContentType());
					}
					else if(fieldValue instanceof FormFile)
					{
						FormFile formFile = (FormFile) fieldValue;
						try(FileInputStream input = new FileInputStream(formFile.getFile()))
						{
							writeFile(dataOutput, propertyName, formFile.getFilename(), input, formFile.getContentType());
						}
					}
					else if(fieldValue != null && (ReflectionUtil.isArray(fieldValue.getClass()) || ReflectionUtil.isCollection(fieldValue.getClass())))
					{
						List<?> elementValues = ReflectionUtil.asCollection(fieldValue);
						for(Object elementValue : elementValues)
						{
							writeProperty(dataOutput, propertyName, elementValue, null);
						}
					}
					else
					{
						writeProperty(dataOutput, propertyName, fieldValue, null);
					}
				}
				dataOutput.writeChars(PREFIX);
				dataOutput.writeChars(getBoundary());
				dataOutput.writeChars(PREFIX);
				dataOutput.writeChar(CARRIAGE_RETURN);
				dataOutput.writeChar(NEW_LINE);
				output.flush();
			}
			catch(IOException e)
			{
				throw new FormException(e);
			}
		}
		else
		{
			super.serialize(map, output);
		}
	}
	
	protected void writeProperty(DataOutputStream output, String name, Object value, PropertyReflector propertyReflector) throws IOException
	{
		String serializedValue = null;
		if(value != null)
		{
			Serializer<?> serializer = getSerializer(value.getClass(), propertyReflector);
			if(serializer != null)
			{
				if(value != null)
				{
					String timeFormat = getTimeFormat(propertyReflector);
					serializedValue = serializer.serialize(value, getTimeZone(propertyReflector), timeFormat);
				}
			}
		}
		else if(getMapperConfig().isSerializeNulls())
		{
			serializedValue = BLANK;
		}
		if(serializedValue != null)
		{
			writeField(output, name, serializedValue);
		}
	}
	
	protected void writeField(DataOutputStream output, String name, String value) throws IOException
	{
		output.writeChars(PREFIX);
		output.writeChars(getBoundary());
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
		output.writeChars(String.format(CONTENT_DISPOSITION, name));
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
		output.writeChars(value);
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
	}
	
	protected void writeFile(DataOutputStream output, String name, String filename, InputStream input) throws IOException
	{
		writeFile(output, name, filename, input, null);
	}
	
	protected void writeFile(DataOutputStream output, String name, String filename, InputStream input, MimeType contentType) throws IOException
	{
		output.writeChars(PREFIX);
		output.writeChars(getBoundary());
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
		output.writeChars(String.format(CONTENT_DISPOSITION, name));
		output.writeChars(String.format(FILENAME, filename));
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
		if(contentType != null)
		{
			output.writeChars(String.format(CONTENT_TYPE, contentType.toString()));
			output.writeChar(CARRIAGE_RETURN);
			output.writeChar(NEW_LINE);
		}
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
		IoUtil.copy(input, output);
		output.writeChar(CARRIAGE_RETURN);
		output.writeChar(NEW_LINE);
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
					String propertyName = propertyReflector.getPropertyName(getMapperType());
					if(propertyName != null)
					{
						Object fieldValue = ReflectionUtil.getFieldValue(propertyReflector.getField(), value);
						if(fieldValue != null && (ReflectionUtil.isArray(fieldValue.getClass()) || ReflectionUtil.isCollection(fieldValue.getClass())))
						{
							List<?> elementValues = ReflectionUtil.asCollection(fieldValue);
							for(Object elementValue : elementValues)
							{
								seperator = writeProperty(writer, propertyName, elementValue, seperator, propertyReflector);
							}
						}
						else
						{
							seperator = writeProperty(writer, propertyName, fieldValue, seperator, propertyReflector);
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
	public void serialize(java.util.Map<String, ?> map, Writer writer)
	{
		if(map == null) throw new IllegalArgumentException("Map cannot be null");
		try
		{
			String seperator = BLANK;
			for(Entry<String, ?> entry : map.entrySet())
			{
				String propertyName = entry.getKey();
				if(propertyName != null)
				{
					Object fieldValue = entry.getValue();
					if(fieldValue != null && (ReflectionUtil.isArray(fieldValue.getClass()) || ReflectionUtil.isCollection(fieldValue.getClass())))
					{
						List<?> elementValues = ReflectionUtil.asCollection(fieldValue);
						for(Object elementValue : elementValues)
						{
							seperator = writeProperty(writer, propertyName, elementValue, seperator, null);
						}
					}
					else
					{
						seperator = writeProperty(writer, propertyName, fieldValue, seperator, null);
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
	
	protected String writeProperty(Writer writer, String name, Object value, String seperator, PropertyReflector propertyReflector) throws IOException
	{
		String serializedValue = null;
		if(value != null)
		{
			Serializer<?> serializer = getSerializer(value.getClass(), propertyReflector);
			if(serializer != null)
			{
				if(value != null)
				{
					String timeFormat = getTimeFormat(propertyReflector);
					serializedValue = serializer.serialize(value, getTimeZone(propertyReflector), timeFormat);
				}
			}
		}
		else if(getMapperConfig().isSerializeNulls())
		{
			serializedValue = BLANK;
		}
		if(serializedValue != null)
		{
			seperator = writeField(writer, name, serializedValue, seperator);
		}
		return seperator;
	}
	
	protected String writeField(Writer writer, String name, String value, String seperator) throws IOException
	{
		writer.write(seperator);
		writer.write(name);
		writer.write(EQUAL);
		writer.write(UrlUtil.encode(value));
		if(BLANK.equals(seperator))
		{
			seperator = String.valueOf(AMPERSAND);
			if(isPrettyPrint())
			{
				seperator += String.valueOf(NEW_LINE);
			}
		}
		return seperator;
	}
	
	@Override
	public <T> T deserialize(InputStream input, Type type)
	{
		T entity = null;
		if(isMultipart())
		{
			if(boundary == null) throw new IllegalArgumentException("Boundary cannot be null");
			try
			{
				BufferedInputStream bufferedInput = input instanceof BufferedInputStream ? (BufferedInputStream) input : new BufferedInputStream(input);
				EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
				Class<T> klass = ReflectionUtil.getTypeClass(type);
				Constructor<T> constructor = klass.getDeclaredConstructor();
				constructor.setAccessible(true);
				entity = constructor.newInstance();
				readFirst(bufferedInput);
				String headers = null;
				while((headers = readHeaders(bufferedInput)) != null)
				{
					String name = null;
					String filename = null;
					MimeType contentType = null;
					Matcher nameMatcher = NAME_PATTERN.matcher(headers);
					if(nameMatcher.find())
					{
						name = nameMatcher.group(1);
						Matcher filenameMatcher = FILENAME_PATTERN.matcher(headers);
						if(filenameMatcher.find())
						{
							filename = filenameMatcher.group(1);
							Matcher contentTypeMatcher = CONTENT_TYPE_PATTERN.matcher(headers);
							if(contentTypeMatcher.find())
							{
								contentType = MimeType.fromString(contentTypeMatcher.group(1));
							}
						}
					}
					byte[] bytes = readValue(bufferedInput);
					if(filename == null)
					{
						setValue(entity, entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector()), new String(bytes, UTF_8));
					}
					else
					{
						PropertyReflector propertyReflector = entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector());
						Class<?> propertyClass = propertyReflector.getFieldClass();
						if(byte[].class.isAssignableFrom(propertyClass))
						{
							ReflectionUtil.setFieldValue(propertyReflector.getField(), entity, bytes);
						}
						else if(FormData.class.isAssignableFrom(propertyClass))
						{
							ReflectionUtil.setFieldValue(propertyReflector.getField(), entity, new FormData(filename, contentType, bytes));
						}
					}
				}
			}
			catch(Exception e)
			{
				throw new FormException(e);
			}
		}
		else
		{
			entity = super.deserialize(input, type);
		}
		return entity;
	}
	
	@Override
	public <T> T deserialize(Reader reader, Type type)
	{
		T entity = null;
		try
		{
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
			Class<T> klass = ReflectionUtil.getTypeClass(type);
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
			Deserializer<?> deserializer = getDeserializer(propertyReflector.getFieldClass(), propertyReflector);
			if(deserializer != null)
			{
				String timeFormat = getTimeFormat(propertyReflector);
				value = propertyReflector.filter(value, getMapperType());
				ReflectionUtil.setFieldValue(propertyReflector.getField(), model, deserializer.deserialize(value, getTimeZone(propertyReflector), timeFormat, propertyReflector.getFieldClass()));
			}
		}
	}
	
	@Override
	public Map<String, Object> deserialize(Reader reader)
	{
		Map<String, Object> map = new Map<String, Object>();
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
	
	protected String readHeaders(BufferedInputStream input) throws IOException
	{
		byte[] newlines = (CRLF + CRLF).getBytes();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		byte b;
		int i = 0;
		byte[] buffer = new byte[1];
		while((input.read(buffer)) != -1)
		{
			b = buffer[0];
			if(b == newlines[i])
			{
				i++;
				if(i == newlines.length)
				{
					break;
				}
				else
				{
					temp.write(b);					
				}
			}
			else
			{
				i = 0;
				if(temp.size() > 0)
				{
					output.write(temp.toByteArray());
					temp.reset();
				}
				output.write(b);
			}
		}
		byte[] headers = output.toByteArray();
		if(!(headers.length >= 2 && headers[0] == DASH && headers[1] == DASH))
		{
			return new String(headers, UTF_8);
		}
		else
		{
			return null;
		}
	}
	
	protected void readFirst(BufferedInputStream input) throws IOException
	{
		readValue(input, BLANK);
	}
	
	protected byte[] readValue(BufferedInputStream input) throws IOException
	{
		return readValue(input, CRLF);
	}
	
	protected byte[] readValue(BufferedInputStream input, String newline) throws IOException
	{
		byte[] boundary = (newline + PREFIX + this.boundary).getBytes();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		byte b;
		int i = 0;
		byte[] buffer = new byte[1];
		while((input.read(buffer)) != -1)
		{
			b = buffer[0];
			if(b == boundary[i])
			{
				i++;
				if(i == boundary.length)
				{
					break;
				}
				else
				{
					temp.write(b);					
				}
			}
			else
			{
				i = 0;
				if(temp.size() > 0)
				{
					output.write(temp.toByteArray());
					temp.reset();
				}
				output.write(b);
			}
		}
		return output.toByteArray();
	}
	
	public <T> T deserialize(java.util.Map<String, String> map, Class<?> klass)
	{
		return deserialize(map, (Type) klass);
	}
	
	public <T> T deserialize(java.util.Map<String, String> map, Generic<T> generic)
	{
		return deserialize(map, generic.getType());
	}
	
	public <T> T deserialize(java.util.Map<String, String> map, Type type)
	{
		T entity = null;
		try
		{
			EntityReflector entityReflector = getMapperReflector().getEntityReflector(type);
			Class<T> klass = ReflectionUtil.getTypeClass(type);
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			entity = constructor.newInstance();
			for(Entry<String, String> entry : map.entrySet())
			{
				String name = entry.getKey();
				String value = entry.getValue();
				setValue(entity, entityReflector.getPropertyReflector(name, getMapperType(), getMapperReflector()), value);
			}
		}
		catch(Exception e)
		{
			throw new FormException(e);
		}
		return entity;
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
