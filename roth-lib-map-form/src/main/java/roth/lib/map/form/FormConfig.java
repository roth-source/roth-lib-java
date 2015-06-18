package roth.lib.map.form;

import roth.lib.map.Config;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;

public class FormConfig extends Config
{
	protected static FormConfig instance;
	
	public FormConfig()
	{
		super();
	}
	
	public static FormConfig get()
	{
		if(instance == null)
		{
			instance = new FormConfig();
		}
		return instance;
	}
	
	public static void set(FormConfig newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public FormConfig setPrettyPrinting(boolean prettyPrinting)
	{
		this.prettyPrinting = prettyPrinting;
		return this;
	}
	
	@Override
	public FormConfig setSerializeNulls(boolean serializeNulls)
	{
		this.serializeNulls = serializeNulls;
		return this;
	}
	
	@Override
	public FormConfig setTimeFormat(String timeFormat)
	{
		super.setTimeFormat(timeFormat);
		return this;
	}
	
	@Override
	public <T> FormConfig setSerializer(Class<T> klass, Serializer<T> serializer)
	{
		serializerMap.put(klass, serializer);
		return this;
	}
	
	@Override
	public <T> FormConfig setDeserializer(Class<T> klass, Deserializer<T> deserializer)
	{
		deserializerMap.put(klass, deserializer);
		return this;
	}
	
}
