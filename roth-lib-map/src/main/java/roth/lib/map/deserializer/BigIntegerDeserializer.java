package roth.lib.map.deserializer;

import java.math.BigInteger;

import roth.lib.map.Deserializer;


public class BigIntegerDeserializer extends Deserializer<BigInteger>
{
	
	public BigIntegerDeserializer()
	{
		
	}
	
	@Override
	public BigInteger deserialize(String value)
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
