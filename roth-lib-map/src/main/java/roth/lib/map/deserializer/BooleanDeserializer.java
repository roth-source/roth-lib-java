package roth.lib.map.deserializer;


public class BooleanDeserializer extends PrimitiveDeserializer<Boolean>
{
	
	public BooleanDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Boolean deserialize(String value)
	{
		Boolean object = isNullable() ? null : false;
		try
		{
			object = Boolean.parseBoolean(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
