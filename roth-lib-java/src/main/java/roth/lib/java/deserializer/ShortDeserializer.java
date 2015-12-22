package roth.lib.java.deserializer;


public class ShortDeserializer extends PrimitiveDeserializer<Short>
{
	
	public ShortDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Short deserialize(String value, String timeFormat)
	{
		Short object = isNullable() ? null : (short) 0;
		try
		{
			object = Short.parseShort(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
