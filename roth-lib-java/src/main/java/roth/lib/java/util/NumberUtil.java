package roth.lib.java.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class NumberUtil
{
	public static NumberFormat FORMAT_AMOUNT = NumberFormat.getInstance(Locale.US);
	public static DecimalFormat FORMAT_WITH_CENTS = new DecimalFormat("###,##0.00");
	public static DecimalFormat FORMAT_WITHOUT_CENTS = new DecimalFormat("###,##0");
	public static Random RANDOM = new Random();
	
	static
	{
		FORMAT_WITH_CENTS.setPositivePrefix("$");
		FORMAT_WITH_CENTS.setNegativePrefix("($");
		FORMAT_WITH_CENTS.setNegativeSuffix(")");
		FORMAT_WITHOUT_CENTS.setPositivePrefix("$");
		FORMAT_WITHOUT_CENTS.setNegativePrefix("($");
		FORMAT_WITHOUT_CENTS.setNegativeSuffix(")");
	}
	
	protected NumberUtil()
	{
		
	}
	
	public static String displayAmount(int amount)
	{
		return displayAmount(amount, false);
	}
	
	public static String displayAmount(int amount, boolean forceNegative)
	{
		return displayAmount(amount, forceNegative, true);
	}
	
	public static String displayAmount(int amount, boolean forceNegative, boolean includeCents)
	{
		amount = forceNegative ? (Math.abs(amount) * -1) : amount;
		return includeCents ? FORMAT_WITH_CENTS.format(amount / 100d) : FORMAT_WITHOUT_CENTS.format(amount / 100d);
	}
	
	public static Integer parseInteger(Object value)
	{
		return parseInteger(String.valueOf(value));
	}
	
	public static Integer parseInteger(String value)
	{
		Integer integer = null;
		try
		{
			integer = Integer.parseInt(value);
		}
		catch(Exception e)
		{
			
		}
		return integer;
	}
	
	public static int parseInt(Object value)
	{
		int integer = 0;
		try
		{
			integer = Integer.parseInt(String.valueOf(value));
		}
		catch(Exception e)
		{
			
		}
		return integer;
	}
	
	public static Long parseLong(String value)
	{
		Long _long = null;
		try
		{
			_long =  Long.parseLong(value);
		}
		catch(Exception e)
		{
			
		}
		return _long;
	}
	
	public static Integer parseAmount(String value)
	{
		Integer integer = null;
		try
		{
			value = value.replaceAll("[^\\d.-]+", "");
			Number number = FORMAT_AMOUNT.parse(value);
			integer = Math.round(number.floatValue() * 100);
		}
		catch(Exception e)
		{
			
		}
		return integer;
	}
	
	public static int random(int min, int max)
	{
		return RANDOM.nextInt((max - min) + 1) + min;
	}

	public static long random(long min, long max)
	{
		return (long) (RANDOM.nextDouble() * ((max - min) + 1)) + min;
	}
	
	public static void main(String[] args)
	{
		String[] values = {"($10,000.0000)","-655.0000","-700.0000","-1.5600","-670.0000","-3635.0000","-655.0000","12.9700","-670.0000","-660.0000","-687.2500","-1000.0000","-700.8900","-663.9200","-645.0000","-670.0000","0.0000","0.0000","0.0000","0.0000","0.0000","0.0000","0.0000","-1213.7800","0.0000"};
		for(String value : values)
		{
			System.out.println(parseAmount(value));
		}
	}
	
}
