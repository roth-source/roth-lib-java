package roth.lib.java.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class CurrencyUtil
{
	
	protected CurrencyUtil()
	{
		
	}
	
	public static String format(Integer value)
	{
		DecimalFormat formatter = new DecimalFormat("'$'#,##0.00");
		return value != null ? formatter.format(new BigDecimal(BigInteger.valueOf(value), 2)) : null;
	}
	
	public static void main(String[] args)
	{
		System.out.println(format(123456789));
	}
	
}
