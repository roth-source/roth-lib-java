package roth.lib.java.deserializer;

import java.math.BigDecimal;


public class BigDecimalDeserializer extends Deserializer<BigDecimal>
{
	
	public BigDecimalDeserializer()
	{
		
	}
	
	@Override
	public BigDecimal deserialize(String value)
	{
		BigDecimal object = null;
		try
		{
			object = new BigDecimal(value);
		}
		catch(Exception e)
		{
			
		}
		return object;
	}
	
}
