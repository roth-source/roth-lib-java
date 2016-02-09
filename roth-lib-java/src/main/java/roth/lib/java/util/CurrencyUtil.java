package roth.lib.java.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CurrencyUtil
{
	
	protected CurrencyUtil()
	{
		
	}
	
	public String format(Integer value)
	{
		return value != null ? new BigDecimal(BigInteger.valueOf(value), 2).toString() : null;
	}
	
}
