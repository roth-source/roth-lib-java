package roth.lib.java.deserializer;

import roth.lib.java.time.TimeZone;
import roth.lib.java.util.EnumUtil;

public class EnumDeserializer extends Deserializer<Enum<?>>
{
	
	public EnumDeserializer()
	{
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Enum<?> deserialize(String value, TimeZone timeZone, String timeFormat, Class<?> klass)
	{
		return EnumUtil.fromString(value, (Class<Enum<?>>) klass);
	}
	
}