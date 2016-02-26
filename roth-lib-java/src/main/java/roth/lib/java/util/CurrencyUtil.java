package roth.lib.java.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class CurrencyUtil
{
	public static final String DEFAULT_PATTERN 	= "'$'#,##0.00";
	public static final String NUMBER_FILTER	= "[^0-9\\-\\.]";
	
	protected CurrencyUtil()
	{
		
	}
	
	public static String format(Number value)
	{
		return format(value, DEFAULT_PATTERN);
	}
	
	public static String format(Number value, String pattern)
	{
		return value != null ? new DecimalFormat(pattern).format(new BigDecimal(BigInteger.valueOf(value.longValue()), 2)) : null;
	}
	
	public static BigDecimal parse(String value)
	{
		BigDecimal number = null;
		try
		{
			if(value != null)
			{
				number = new BigDecimal(value.replaceAll(NUMBER_FILTER, "")).multiply(new BigDecimal(100));
			}
		}
		catch(Exception e)
		{
			
		}
		return number;
	}
	
	public static void main(String[] args)
	{
		System.out.println(parse("$   -10,000"));
	}
	
}
