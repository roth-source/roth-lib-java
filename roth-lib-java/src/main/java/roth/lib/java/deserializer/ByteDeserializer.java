package roth.lib.java.deserializer;


public class ByteDeserializer extends PrimitiveDeserializer<Byte>
{
	
	public ByteDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Byte deserialize(String value)
	{
		Byte object = isNullable() ? null : (byte) 0;
		try
		{
			object = Byte.parseByte(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
