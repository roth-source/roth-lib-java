package roth.lib.java.map.deserializer;

import java.math.BigDecimal;


public class BigDecimalDeserializer extends Deserializer<BigDecimal>
{
	
	public BigDecimalDeserializer()
	{
		
	}
	
	@Override
	public BigDecimal deserialize(String value, String timeFormat)
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
