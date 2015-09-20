package roth.lib.java.map.deserializer;


public class DoubleDeserializer extends PrimitiveDeserializer<Double>
{
	
	public DoubleDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Double deserialize(String value, String timeFormat)
	{
		Double object = isNullable() ? null : 0d;
		try
		{
			object = Double.parseDouble(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
