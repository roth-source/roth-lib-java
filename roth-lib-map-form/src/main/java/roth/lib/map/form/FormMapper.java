package roth.lib.map.form;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import roth.lib.annotation.Property;
import roth.lib.map.Deserializer;
import roth.lib.map.PropertyField;
import roth.lib.map.SerialMapper;
import roth.lib.map.Serializer;
import roth.lib.map.mapper.PropertyMapper;
import roth.lib.map.util.MapperUtil;

public class FormMapper extends SerialMapper<FormConfig>
{
	protected static FormMapper instance;
	
	public FormMapper()
	{
		super();
		propertyMappers.add(new PropertyMapper<Property>(Property.class)
		{
			@Override
			public String getPropertyName(Field field, Property property)
			{
				if(property != null && property.form())
				{
					if(isValid(property.formName()))
					{
						return property.formName();
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
	
	public static FormMapper get()
	{
		if(instance == null)
		{
			instance = new FormMapper();
		}
		return instance;
	}
	
	public static void set(FormMapper newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public FormConfig defaultConfig()
	{
		return new FormConfig();
	}
	
	@Override
	public void serialize(Object object, Writer writer, FormConfig config)
	{
		if(object == null) throw new IllegalArgumentException("Object cannot be null");
		try
		{
			String seperator = BLANK;
			for(PropertyField propertyField : getPropertyFields(object.getClass()))
			{
				Class<?> propertyClass = propertyField.getFieldClass();
				String propertyName = propertyField.getPropertyName();
				if(propertyName != null)
				{
					Serializer<?> serializer = config.getSerializer(propertyClass);
					if(serializer != null)
					{
						String serializedObject = null;
						Object propertyObject = getPropertyObject(propertyField.getField(), object);
						if(propertyObject != null)
						{
							serializedObject = serializer.serializeInternal(propertyObject);
						}
						else if(config.isSerializeNulls())
						{
							serializedObject = BLANK;
						}
						if(serializedObject != null)
						{
							writer.write(seperator);
							writer.write(propertyName);
							writer.write(EQUAL);
							writer.write(URLEncoder.encode(serializedObject, UTF_8.name()));
							if(BLANK.equals(seperator))
							{
								seperator += AMPERSAND;
								if(config.isPrettyPrinting())
								{
									seperator += NEW_LINE;
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
	public void serialize(Map<String, ?> map, Writer writer, FormConfig config)
	{
		if(map == null) throw new IllegalArgumentException("Object cannot be null");
		try
		{
			String seperator = BLANK;
			for(Entry<String, ?> entry : map.entrySet())
			{
				String propertyName = entry.getKey();
				Object propertyObject = entry.getValue();
				String serializedObject = null;
				if(propertyObject != null)
				{
					Serializer<?> serializer = config.getSerializer(propertyObject.getClass());
					if(serializer != null)
					{
						serializedObject = serializer.serializeInternal(propertyObject);
					}
				}
				else if(config.isSerializeNulls())
				{
					serializedObject = BLANK;
				}
				if(serializedObject != null)
				{
					writer.write(seperator);
					writer.write(propertyName);
					writer.write(EQUAL);
					writer.write(URLEncoder.encode(serializedObject, UTF_8.name()));
					if(BLANK.equals(seperator))
					{
						seperator += AMPERSAND;
						if(config.isPrettyPrinting())
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
	public void serialize(Object object, OutputStream output, FormConfig config)
	{
		if(object == null) throw new IllegalArgumentException("Object cannot be null");
		if(!(object instanceof FormBoundary))
		{
			super.serialize(object, output, config);
		}
		else
		{
			try
			{
				String boundary = ((FormBoundary) object).getBoundary();
				MultiPartFormOutputStream formOutput = new MultiPartFormOutputStream(output, boundary);
				//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
				for(PropertyField propertyField : getPropertyFields(object.getClass()))
				{
					Class<?> propertyClass = propertyField.getFieldClass();
					String propertyName = propertyField.getPropertyName();
					if(propertyName != null)
					{
						Object propertyObject = getPropertyObject(propertyField.getField(), object);
						Serializer<?> serializer = config.getSerializer(propertyClass);
						if(serializer != null)
						{
							String serializedObject = null;
							if(propertyObject != null)
							{
								serializedObject = serializer.serializeInternal(propertyObject);
							}
							else if(config.isSerializeNulls())
							{
								serializedObject = BLANK;
							}
							if(serializedObject != null)
							{
								//writeBoundary(dataOutput, boundary, config);
								//writeString(dataOutput, propertyName, serializedObject, config);
								formOutput.writeField(propertyName, serializedObject);
							}
						}
						else if(propertyObject instanceof FormData)
						{
							//writeBoundary(dataOutput, boundary, config);
							//writeFormData(dataOutput, propertyName, (FormData) propertyObject, config);
						}
						else if(propertyObject instanceof FormFile)
						{
							//writeBoundary(dataOutput, boundary, config);
							//writeFormFile(dataOutput, propertyName, (FormFile) propertyObject, config);
							FormFile formFile = (FormFile) propertyObject;
							formOutput.writeFile(propertyName, formFile.getContentType(), formFile.getFile());
						}
					}
				}
				//output.write(("--" + boundary +"--").getBytes());
				//writeNewLine(dataOutput, config);
				//writeNewLine(dataOutput, config);
				//output.flush();
				formOutput.close();
			}
			catch(IOException e)
			{
				throw new FormException(e);
			}
		}
	}
	/*
	protected void writeNewLine(DataOutputStream output, FormConfig config) throws IOException
	{
		output.write(CARRIAGE_RETURN);
		output.write(NEW_LINE);
	}
	
	protected void writeBoundary(DataOutputStream output, String boundary, FormConfig config) throws IOException
	{
		output.writeBytes("--" + boundary);
		writeNewLine(output, config);
	}
	
	protected void writeName(DataOutputStream output, String name, FormConfig config) throws IOException
	{
		output.writeBytes("Content-Disposition: form-data; ");
		output.write(("name=\"" + name + "\"").getBytes());
	}
	
	protected void writeString(DataOutputStream output, String name, String data, FormConfig config) throws IOException
	{
		writeName(output, name, config);
		writeNewLine(output, config);
		writeNewLine(output, config);
		output.write(data.getBytes());
		writeNewLine(output, config);
	}
	
	protected void writeFilename(DataOutputStream output, String filename, FormConfig config) throws IOException
	{
		output.writeBytes("; filename=\"" + filename + "\"");
	}
	
	protected void writeContentType(DataOutputStream output, String contentType, FormConfig config) throws IOException
	{
		output.writeBytes("Content-Type: " + contentType);
	}
	
	protected void writeFormData(DataOutputStream output, String name, FormData formData, FormConfig config) throws IOException
	{
		writeName(output, name, config);
		writeFilename(output, formData.getFilename(), config);
		writeNewLine(output, config);
		writeContentType(output, formData.getContentType(), config);
		writeNewLine(output, config);
		writeNewLine(output, config);
		if(!config.isPrettyPrinting())
		{
			output.write(formData.getOutput().toByteArray());
		}
		writeNewLine(output, config);
	}
	
	protected void writeFormFile(DataOutputStream output, String name, FormFile formFile, FormConfig config) throws IOException
	{
		writeName(output, name, config);
		writeFilename(output, formFile.getFilename(), config);
		writeNewLine(output, config);
		writeContentType(output, formFile.getContentType(), config);
		writeNewLine(output, config);
		writeNewLine(output, config);
		if(!config.isPrettyPrinting())
		{
			try(RandomAccessFile randomAccessFile = new RandomAccessFile(formFile.getFile(), "r");)
			{
				randomAccessFile.seek(formFile.getOffset());
				long length = formFile.getLength();
				int n = 0;
				byte[] buffer = new byte[1024 * 4];
				int bufferLength = buffer.length;
				while((n = randomAccessFile.read(buffer, 0, bufferLength)) > 0)
				{
					output.write(buffer);
					length -= n;
					bufferLength = (int) Math.min(bufferLength, length);
				}
			}
		}
		writeNewLine(output, config);
	}
	*/
	@Override
	public <T> T deserialize(Reader reader, Type type, FormConfig config)
	{
		T model = null;
		try
		{
			LinkedHashMap<String, PropertyField> propertyNameFieldMap = getPropertyNameFieldMap(type);
			Class<T> klass = MapperUtil.getClass(type);
			Constructor<T> constructor = klass.getDeclaredConstructor();
			constructor.setAccessible(true);
			model = constructor.newInstance();
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
						setValue(config, model, propertyNameFieldMap.get(name), URLDecoder.decode(builder.toString(), UTF_8.name()));
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
			setValue(config, model, propertyNameFieldMap.get(name), URLDecoder.decode(builder.toString(), UTF_8.name()));
		}
		catch(Exception e)
		{
			throw new FormException(e);
		}
		return model;
	}
	
	protected void setValue(FormConfig config, Object model, PropertyField propertyField, String value) throws Exception
	{
		if(propertyField != null)
		{
			Deserializer<?> deserializer = config.getDeserializer(propertyField.getFieldClass());
			if(deserializer != null)
			{
				propertyField.getField().set(model, deserializer.deserialize(value, propertyField.getFieldClass()));
			}
		}
	}
	
	@Override
	public LinkedHashMap<String, Object> deserialize(Reader reader, FormConfig config)
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
						map.put(name, URLDecoder.decode(builder.toString(), UTF_8.name()));
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
			map.put(name, URLDecoder.decode(builder.toString(), UTF_8.name()));
		}
		catch(Exception e)
		{
			throw new FormException(e);
		}
		return map;
	}
	
	public LinkedHashMap<String, String> convert(Object object, FormConfig config)
	{
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for(PropertyField propertyField : getPropertyFields(object.getClass()))
		{
			Class<?> propertyClass = propertyField.getFieldClass();
			String propertyName = propertyField.getPropertyName();
			if(propertyName != null)
			{
				Serializer<?> serializer = config.getSerializer(propertyClass);
				if(serializer != null)
				{
					String serializedObject = null;
					Object propertyObject = getPropertyObject(propertyField.getField(), object);
					if(propertyObject != null)
					{
						serializedObject = serializer.serializeInternal(propertyObject);
					}
					else if(config.isSerializeNulls())
					{
						serializedObject = BLANK;
					}
					if(serializedObject != null)
					{
						map.put(propertyName, serializedObject);
					}
				}
			}
		}
		return map;
	}
	
}
