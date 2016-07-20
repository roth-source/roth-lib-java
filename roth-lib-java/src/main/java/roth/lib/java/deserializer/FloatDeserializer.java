package roth.lib.java.deserializer;


public class FloatDeserializer extends PrimitiveDeserializer<Float>
{
	
	public FloatDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Float deserialize(String value)
	{
		Float object = isNullable() ? null : 0f;
		try
		{
			object = Float.parseFloat(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
