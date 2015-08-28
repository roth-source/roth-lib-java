package roth.lib.map.deserializer;

import java.math.BigInteger;


public class BigIntegerDeserializer extends Deserializer<BigInteger>
{
	
	public BigIntegerDeserializer()
	{
		
	}
	
	@Override
	public BigInteger deserialize(String value, String timeFormat)
	{
		BigInteger object = null;
		try
		{
			object = new BigInteger(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
