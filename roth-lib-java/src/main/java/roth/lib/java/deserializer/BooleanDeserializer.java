package roth.lib.java.deserializer;


public class BooleanDeserializer extends PrimitiveDeserializer<Boolean>
{
	
	public BooleanDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Boolean deserialize(String value, String timeFormat)
	{
		Boolean object = isNullable() ? null : false;
		try
		{
			if("true".equalsIgnoreCase(value))
			{
				object = true;
			}
			else if("false".equalsIgnoreCase(value))
			{
				object = false;
			}
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
	public static void main(String[] args)
	{
		System.out.println(new BooleanDeserializer(true).deserialize("null", null));
	}
	
}
