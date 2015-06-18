package roth.lib.map.deserializer;


public class IntegerDeserializer extends PrimitiveDeserializer<Integer>
{
	
	public IntegerDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Integer deserialize(String value)
	{
		Integer object = isNullable() ? null : 0;
		try
		{
			object = Integer.parseInt(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
