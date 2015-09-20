package roth.lib.java.map.deserializer;

import roth.lib.java.util.EnumUtil;

public class EnumDeserializer extends Deserializer<Enum<?>>
{
	
	public EnumDeserializer()
	{
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Enum<?> deserialize(String value, String timeFormat, Class<?> klass)
	{
		return EnumUtil.fromString(value, (Class<Enum<?>>) klass);
	}
	
	@Override
	public Enum<?> deserialize(String value, String timeFormat)
	{
		return null;
	}
	
}