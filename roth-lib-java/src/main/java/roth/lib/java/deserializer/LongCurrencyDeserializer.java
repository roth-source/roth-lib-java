package roth.lib.java.deserializer;

import java.math.BigDecimal;

import roth.lib.java.util.CurrencyUtil;

public class LongCurrencyDeserializer extends Deserializer<Long>
{
	
	@Override
	public Long deserialize(String value)
	{
		BigDecimal number = CurrencyUtil.parse(value);
		return number != null ? number.longValue() : null;
	}
	
}
