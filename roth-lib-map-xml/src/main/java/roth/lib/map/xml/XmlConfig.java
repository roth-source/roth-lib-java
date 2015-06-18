package roth.lib.map.xml;

import roth.lib.map.Config;
import roth.lib.map.Deserializer;
import roth.lib.map.Serializer;

public class XmlConfig extends Config
{
	protected static XmlConfig instance;
	
	public XmlConfig()
	{
		super();
	}
	
	public static XmlConfig get()
	{
		if(instance == null)
		{
			instance = new XmlConfig();
		}
		return instance;
	}
	
	public static void set(XmlConfig newInstance)
	{
		instance = newInstance;
	}
	
	@Override
	public XmlConfig setPrettyPrinting(boolean prettyPrinting)
	{
		this.prettyPrinting = prettyPrinting;
		return this;
	}
	
	@Override
	public XmlConfig setSerializeNulls(boolean serializeNulls)
	{
		this.serializeNulls = serializeNulls;
		return this;
	}
	
	@Override
	public XmlConfig setTimeFormat(String timeFormat)
	{
		super.setTimeFormat(timeFormat);
		return this;
	}
	
	@Override
	public <T> XmlConfig setSerializer(Class<T> klass, Serializer<T> serializer)
	{
		serializerMap.put(klass, serializer);
		return this;
	}
	
	@Override
	public <T> Config setDeserializer(Class<T> klass, Deserializer<T> deserializer)
	{
		deserializerMap.put(klass, deserializer);
		return this;
	}
	
}
