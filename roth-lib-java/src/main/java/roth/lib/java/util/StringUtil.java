package roth.lib.java.util;

import java.util.Calendar;
import java.util.Date;


public class StringUtil
{
	
	protected StringUtil()
	{
		
	}
	
	public static boolean isValid(String value)
	{
		return value != null && !value.isEmpty();
	}
	
	public static String flatten(String value)
	{
		if(value != null)
		{
			return value.replaceAll("(\\r\\n|\\n|\\t)", "").replaceAll("\\s{2,}", " ");
		}
		return null;
	}
	
	public static String init(String value)
	{
		return value != null ? value : "";
	}
	
	public static String limit(String value, int length)
	{
		return value != null && value.length() > length ? value.substring(0, length) : value;
	}
	
	public static String padLeft(String value, int length, char pad)
	{
		StringBuilder builder = new StringBuilder();
		int valueLength = 0;
		if(value != null)
		{
			builder.append(value);
			valueLength = value.length();
		}
		for(int i = valueLength; i < length; i++)
		{
			builder.insert(0, pad);
		}
		return builder.toString();
	}
	
	public static String padLeftLimit(String value, int length, char pad)
	{
		return limit(padLeft(value, length, pad), length);
	}
	
	public static String padRight(String value, int length, char pad)
	{
		StringBuilder builder = new StringBuilder();
		int valueLength = 0;
		if(value != null)
		{
			builder.append(value);
			valueLength = value.length();
		}
		for(int i = valueLength; i < length; i++)
		{
			builder.append(pad);
		}
		return builder.toString();
	}
	
	public static String padRightLimit(String value, int length, char pad)
	{
		return limit(padRight(value, length, pad), length);
	}
	
	public static String valueOf(Object object)
	{
		String value = null;
		if(object != null)
		{
			if(object instanceof Boolean || object instanceof Number || object instanceof Character || object instanceof String || object instanceof Enum)
			{
				value = String.valueOf(object);
			}
			else if(object instanceof Date)
			{
				value = String.valueOf(((Date) object).getTime());
			}
			else if(object instanceof Calendar)
			{
				value = String.valueOf(((Calendar) object).getTimeInMillis());
			}
		}
		return value;
	}
	
	public static String comma(String[] values)
	{
		StringBuilder builder = new StringBuilder();
		if(values != null && values.length > 0)
		{
			String seperator = "";
			for(String value : values)
			{
				builder.append(seperator);
				builder.append(value);
				if("".equals(seperator))
				{
					seperator = ",";
				}
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args)
	{
		String value = "1234567890";
		char pad = '0';
		System.out.println("limit");
		System.out.println(limit(value, 5));
		System.out.println(limit(value, 15));
		System.out.println(limit(null, 5));
		System.out.println("padLeft");
		System.out.println(padLeft(value, 5, pad));
		System.out.println(padLeft(value, 15, pad));
		System.out.println(padLeft(null, 5, pad));
		System.out.println("padRight");
		System.out.println(padRight(value, 5, pad));
		System.out.println(padRight(value, 15, pad));
		System.out.println(padRight(null, 5, pad));
		System.out.println("padLeftLimit");
		System.out.println(padLeftLimit(value, 5, pad));
		System.out.println(padLeftLimit(value, 15, pad));
		System.out.println(padLeftLimit(null, 5, pad));
		System.out.println("padRightLimit");
		System.out.println(padRightLimit(value, 5, pad));
		System.out.println(padRightLimit(value, 15, pad));
		System.out.println(padRightLimit(null, 5, pad));
		
	}
	
}
