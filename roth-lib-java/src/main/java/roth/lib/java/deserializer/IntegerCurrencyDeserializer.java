package roth.lib.java.deserializer;

import java.math.BigDecimal;

import roth.lib.java.util.CurrencyUtil;

public class IntegerCurrencyDeserializer extends Deserializer<Integer>
{
	
	@Override
	public Integer deserialize(String value)
	{
		BigDecimal number = CurrencyUtil.parse(value);
		return number != null ? number.intValue() : null;
	}
	
}
