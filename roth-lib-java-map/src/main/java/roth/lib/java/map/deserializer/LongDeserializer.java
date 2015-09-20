package roth.lib.java.map.deserializer;


public class LongDeserializer extends PrimitiveDeserializer<Long>
{
	
	public LongDeserializer(boolean nullable)
	{
		super(nullable);
	}
	
	@Override
	public Long deserialize(String value, String timeFormat)
	{
		Long object = isNullable() ? null : 0L;
		try
		{
			object = Long.parseLong(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
