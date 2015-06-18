package roth.lib.map.deserializer;


public class ShortDeserializer extends PrimitiveDeserializer<Short>
{
	
	public ShortDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Short deserialize(String value)
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
