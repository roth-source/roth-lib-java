package roth.lib.java.db.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteUtil
{
	
	protected ByteUtil()
	{
		
	}
	
	public static long maxValue(byte length)
	{
		return (long) Math.pow(2, Byte.SIZE * length - 1);
	}
	
	public static byte[] toBytes(Number number, int byteLength)
	{
		if(byteLength <= Long.BYTES)
		{
			ByteBuffer buffer = ByteBuffer.allocate(byteLength);
			buffer.put(Arrays.copyOfRange(ByteBuffer.allocate(Long.BYTES).putLong(number.longValue()).array(), Long.BYTES - byteLength, Long.BYTES));
			return buffer.array();
		}
		else
		{
			throw new IllegalArgumentException("length is larger than long bytes");
		}
	}
	
	public static byte[] toBytes(BigDecimal number, int byteLength)
	{
		return toBytes(number.unscaledValue(), byteLength);
	}
	
	public static byte[] toBytes(BigInteger number, int byteLength)
	{
		ByteBuffer buffer = ByteBuffer.allocate(byteLength);
		byte[] bytes = number.toByteArray();
		buffer.position(byteLength - bytes.length);
		buffer.put(bytes);
		return buffer.array();
	}
	
	public static boolean toBoolean(byte value)
	{
		return value > 0;
	}
	
	public static short toShort(byte[] bytes)
	{
		return toShort(ByteBuffer.wrap(bytes));
	}
	
	public static short toShort(ByteBuffer buffer)
	{
		return toByteBuffer(buffer, Short.BYTES).getShort();
	}
	
	public static int toInt(byte[] bytes)
	{
		return toInt(ByteBuffer.wrap(bytes));
	}
	
	public static int toInt(ByteBuffer buffer)
	{
		return toByteBuffer(buffer, Integer.BYTES).getInt();
	}
	
	public static long toLong(byte[] bytes)
	{
		return toLong(ByteBuffer.wrap(bytes));
	}
	
	public static long toLong(ByteBuffer buffer)
	{
		return toByteBuffer(buffer, Long.BYTES).getLong();
	}
	
	public static float toFloat(byte[] bytes)
	{
		return toFloat(ByteBuffer.wrap(bytes));
	}
	
	public static float toFloat(ByteBuffer buffer)
	{
		return toByteBuffer(buffer, Float.BYTES).getFloat();
	}
	
	public static double toDouble(byte[] bytes)
	{
		return toDouble(ByteBuffer.wrap(bytes));
	}
	
	public static double toDouble(ByteBuffer buffer)
	{
		return toByteBuffer(buffer, Double.BYTES).getDouble();
	}
	
	public static Number toNumber(byte[] bytes)
	{
		return toNumber(ByteBuffer.wrap(bytes));
	}
	
	public static Number toNumber(ByteBuffer buffer)
	{
		Number number = null;
		if(buffer.remaining() <= Short.BYTES)
		{
			number = toShort(buffer);
		}
		else if(buffer.remaining() <= Integer.BYTES)
		{
			number = toInt(buffer);
		}
		else if(buffer.remaining() <= Long.BYTES)
		{
			number = toLong(buffer);
		}
		return number;
	}
	
	protected static ByteBuffer toByteBuffer(ByteBuffer buffer, int length)
	{
		if(length > buffer.remaining())
		{
			return (ByteBuffer)((ByteBuffer) ByteBuffer.allocate(length).position(length - buffer.remaining())).put(buffer.array()).rewind();
		}
		else
		{
			return buffer;
		}
	}
	
	public static BigInteger toBigInteger(byte[] bytes)
	{
		return new BigInteger(bytes);
	}
	
	public static BigInteger toBigInteger(ByteBuffer buffer)
	{
		return new BigInteger(buffer.array());
	}
	
	public static BigDecimal toBigDecimal(byte[] bytes, int scale)
	{
		return new BigDecimal(toBigInteger(bytes), scale);
	}
	
	public static BigDecimal toBigDecimal(ByteBuffer buffer, int scale)
	{
		return new BigDecimal(toBigInteger(buffer), scale);
	}
	
	public static void main(String[] args)
	{
		BigDecimal number = new BigDecimal("123456789");
		System.out.println(Arrays.toString(toBytes(number, 10)));
	}
	
}
