package roth.lib.java.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BaseUtil
{
	public static final int BASE_16 = 16;
	public static final int BASE_36 = 36;
	public static final int BASE_62 = 62;
	
	protected static final char[] CHARACTERS =
	{
		// base 16
		'0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
		
		// base 36
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		
		// base 62
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	protected static final String CHARACTER_STRING = new String(CHARACTERS);
	public static final char BASE_PAD = CHARACTERS[0];
	
	protected BaseUtil()
	{
		
	}
	
	public static String encodeBase16(String value)
	{
		return encode(value, BASE_16);
	}
	
	public static String encodeBase36(String value)
	{
		return encode(value, BASE_36);
	}
	
	public static String encodeBase62(String value)
	{
		return encode(value, BASE_62);
	}
	
	public static String encode(String value, int base)
	{
		return encode(value.getBytes(), base);
	}
	
	public static String encodeBase16(byte[] bytes)
	{
		return encode(bytes, BASE_16);
	}
	
	public static String encodeBase36(byte[] bytes)
	{
		return encode(bytes, BASE_36);
	}
	
	public static String encodeBase62(byte[] bytes)
	{
		return encode(bytes, BASE_62);
	}
	
	public static String encode(byte[] bytes, int base)
	{
		return encode(new BigInteger(1, bytes), base);
	}
	
	public static String encodeBase16(long number)
	{
		return encode(number, BASE_16);
	}
	
	public static String encodeBase36(long number)
	{
		return encode(number, BASE_36);
	}
	
	public static String encodeBase62(long number)
	{
		return encode(number, BASE_62);
	}
	
	public static String encode(long number, int base)
	{
		return encode(BigInteger.valueOf(number), base);
	}
	
	public static String encodeBase16(BigInteger number)
	{
		return encode(number, BASE_16);
	}
	
	public static String encodeBase36(BigInteger number)
	{
		return encode(number, BASE_36);
	}
	
	public static String encodeBase62(BigInteger number)
	{
		return encode(number, BASE_62);
	}
	
	public static String encode(BigInteger number, int base)
	{
		return encode(number, BigInteger.valueOf(base));
	}
	
	protected static String encode(BigInteger number, BigInteger base)
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
		return builder.reverse().toString();
	}
	
	public static String decodeStringBase16(String value)
	{
		return decodeString(value.toLowerCase(), BASE_16);
	}
	
	public static String decodeStringBase36(String value)
	{
		return decodeString(value.toLowerCase(), BASE_36);
	}
	
	public static String decodeStringBase62(String value)
	{
		return decodeString(value, BASE_62);
	}
	
	public static String decodeString(String value, int base)
	{
		return new String(decodeBytes(value, base));
	}
	
	public static byte[] decodeBytesBase16(String value)
	{
		return decodeBytes(value.toLowerCase(), BASE_16);
	}
	
	public static byte[] decodeBytesBase36(String value)
	{
		return decodeBytes(value.toLowerCase(), BASE_36);
	}
	
	public static byte[] decodeBytesBase62(String value)
	{
		return decodeBytes(value, BASE_62);
	}
	
	public static byte[] decodeBytes(String value, int base)
	{
		return decode(value, BigInteger.valueOf(base)).toByteArray();
	}
	
	public static BigInteger decodeBase16(String value)
	{
		return decode(value.toLowerCase(), BASE_16);
	}
	
	public static BigInteger decodeBase36(String value)
	{
		return decode(value.toLowerCase(), BASE_36);
	}
	
	public static BigInteger decodeBase62(String value)
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
	
	public static void main(String[] args)
	{
		int value = 238327;
		for(int i = value; i > 3843; i--)
		{
			ByteBuffer buffer = ByteBuffer.allocate(4);
			buffer.putInt(i);
			System.out.println(Arrays.toString(Arrays.copyOfRange(buffer.array(), 1, 4)));
		}
	}
	
}
