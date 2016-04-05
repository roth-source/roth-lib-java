package roth.lib.java.util;

import java.math.BigInteger;
import java.util.Base64;

public class BaseUtil
{
	public static final int BASE_16 = 16;
	public static final int BASE_36 = 36;
	public static final int BASE_62 = 62;
	public static final int BASE_64 = 64;
	
	protected static final char[] CHARACTERS =
	{
		// base 16
		'0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
		
		// base 36
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		
		// base 62
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	public static final String CHARACTER_STRING = new String(CHARACTERS);
	public static final char BASE_PAD = CHARACTERS[0];
	
	protected BaseUtil()
	{
		
	}
	
	public static String encode16(String value)
	{
		return encode(value, BASE_16);
	}
	
	public static String encode36(String value)
	{
		return encode(value, BASE_36);
	}
	
	public static String encode62(String value)
	{
		return encode(value, BASE_62);
	}
	
	public static String encode(String value, int base)
	{
		return encode(value.getBytes(), base);
	}
	
	public static String encode16(byte[] bytes)
	{
		return encode(bytes, BASE_16);
	}
	
	public static String encode36(byte[] bytes)
	{
		return encode(bytes, BASE_36);
	}
	
	public static String encode62(byte[] bytes)
	{
		return encode(bytes, BASE_62);
	}
	
	public static String encode64(byte[] bytes)
	{
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static String encode(byte[] bytes, int base)
	{
		if(BASE_64 == base)
		{
			return encode64(bytes);
		}
		else
		{
			return encode(new BigInteger(1, bytes), base);
		}
	}
	
	public static String encode16(long number)
	{
		return encode(number, BASE_16);
	}
	
	public static String encode36(long number)
	{
		return encode(number, BASE_36);
	}
	
	public static String encode62(long number)
	{
		return encode(number, BASE_62);
	}
	
	public static String encode(long number, int base)
	{
		return encode(BigInteger.valueOf(number), base);
	}
	
	public static String encode16(BigInteger number)
	{
		return encode(number, BASE_16);
	}
	
	public static String encode36(BigInteger number)
	{
		return encode(number, BASE_36);
	}
	
	public static String encode62(BigInteger number)
	{
		return encode(number, BASE_62);
	}
	
	public static String encode(BigInteger number, int base)
	{
		return encode(number, null, BigInteger.valueOf(base));
	}

	public static String encode16(String value, int length)
	{
		return encode(value, length, BASE_16);
	}
	
	public static String encode36(String value, int length)
	{
		return encode(value, length, BASE_36);
	}
	
	public static String encode62(String value, int length)
	{
		return encode(value, length, BASE_62);
	}
	
	public static String encode(String value, int length, int base)
	{
		return encode(value.getBytes(), length, base);
	}
	
	public static String encode16(byte[] bytes, int length)
	{
		return encode(bytes, length, BASE_16);
	}
	
	public static String encode36(byte[] bytes, int length)
	{
		return encode(bytes, length, BASE_36);
	}
	
	public static String encode62(byte[] bytes, int length)
	{
		return encode(bytes, length, BASE_62);
	}
	
	public static String encode(byte[] bytes, int length, int base)
	{
		return encode(new BigInteger(1, bytes), length, base);
	}
	
	public static String encode16(long number, int length)
	{
		return encode(number, length, BASE_16);
	}
	
	public static String encode36(long number, int length)
	{
		return encode(number, length, BASE_36);
	}
	
	public static String encode62(long number, int length)
	{
		return encode(number, length, BASE_62);
	}
	
	public static String encode(long number, int length, int base)
	{
		return encode(BigInteger.valueOf(number), length, base);
	}
	
	public static String encode16(BigInteger number, int length)
	{
		return encode(number, length, BASE_16);
	}
	
	public static String encode36(BigInteger number, int length)
	{
		return encode(number, length, BASE_36);
	}
	
	public static String encode62(BigInteger number, int length)
	{
		return encode(number, length, BASE_62);
	}
	
	public static String encode(BigInteger number, int length, int base)
	{
		return encode(number, length, BigInteger.valueOf(base));
	}
	
	protected static String encode(BigInteger number, Integer length, BigInteger base)
	{
		StringBuilder builder = new StringBuilder();
		number = number.abs();
		base = base.max(BigInteger.valueOf(2));
		base = base.min(BigInteger.valueOf(CHARACTERS.length));
		while(number.signum() == 1)
		{
			builder.append(CHARACTERS[number.mod(base).intValue()]);
			number = number.divide(base);
		}
		if(length != null)
		{
			return StringUtil.padLeftLimit(builder.reverse().toString(), length, BASE_PAD);
		}
		else
		{
			return builder.reverse().toString();
		}
	}
	
	public static String decodeString16(String value)
	{
		return decodeString(value.toLowerCase(), BASE_16);
	}
	
	public static String decodeString36(String value)
	{
		return decodeString(value.toLowerCase(), BASE_36);
	}
	
	public static String decodeString62(String value)
	{
		return decodeString(value, BASE_62);
	}
	
	public static String decodeString64(String value)
	{
		return decodeString(value, BASE_64);
	}
	
	public static String decodeString(String value, int base)
	{
		return new String(decodeBytes(value, base));
	}
	
	public static byte[] decodeBytes16(String value)
	{
		return decodeBytes(value.toLowerCase(), BASE_16);
	}
	
	public static byte[] decodeBytes36(String value)
	{
		return decodeBytes(value.toLowerCase(), BASE_36);
	}
	
	public static byte[] decodeBytes62(String value)
	{
		return decodeBytes(value, BASE_62);
	}
	
	public static byte[] decodeBytes64(String value)
	{
		return Base64.getDecoder().decode(value.getBytes());
	}
	
	public static byte[] decodeBytes(String value, int base)
	{
		if(BASE_64 == base)
		{
			return decodeBytes64(value);
		}
		else
		{
			return decode(value, BigInteger.valueOf(base)).toByteArray();
		}
	}
	
	public static BigInteger decode16(String value)
	{
		return decode(value.toLowerCase(), BASE_16);
	}
	
	public static BigInteger decode36(String value)
	{
		return decode(value.toLowerCase(), BASE_36);
	}
	
	public static BigInteger decode62(String value)
	{
		return decode(value, BASE_62);
	}
	
	public static BigInteger decode(String value, int base)
	{
		return decode(value, BigInteger.valueOf(base));
	}
	
	protected static BigInteger decode(String value, BigInteger base)
	{
		BigInteger number = BigInteger.valueOf(0);
		if(value != null)
		{
			int baseInt = base.intValue();
			int length = value.length();
			for(int i = 0; i < length; i++)
			{
				int index = CHARACTER_STRING.indexOf(value.charAt(i));
				if(index != -1 && index < baseInt)
				{
					number = number.multiply(base).add(BigInteger.valueOf(index));
				}
			}
		}
		return number;
	}
	
	public static void main(String[] args) throws Exception
	{
		
	}
	
}
