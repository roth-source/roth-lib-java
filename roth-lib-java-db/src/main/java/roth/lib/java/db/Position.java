package roth.lib.java.db;

import java.nio.ByteBuffer;
import java.util.Arrays;

import roth.lib.java.db.util.ByteUtil;

public class Position
{
	Number offset;
	Number length;
	
	public Position(Number offset)
	{
		this(offset, null);
	}
	
	public Position(Number offset, Number length)
	{
		setOffset(offset);
		setLength(length);
	}
	
	public Long getOffset()
	{
		return offset.longValue();
	}
	
	public Long getLength()
	{
		return length != null ? length.longValue() : null;
	}
	
	public Position setOffset(Number offset)
	{
		this.offset = offset;
		return this;
	}
	
	public Position setLength(Number length)
	{
		this.length = length;
		return this;
	}
	
	public byte[] toBytes(byte offsetByteLength, byte lengthByteLength)
	{
		ByteBuffer buffer = ByteBuffer.allocate(offsetByteLength + lengthByteLength);
		putBytes(buffer, offset, offsetByteLength);
		putBytes(buffer, length, lengthByteLength);
		return buffer.array();
	}
	
	protected void putBytes(ByteBuffer buffer, Number number, byte byteLength)
	{
		if(number != null)
		{
			buffer.put(ByteUtil.toBytes(number, byteLength));
		}
	}
	
	public static Position fromBytes(byte[] bytes, byte offsetByteLength, byte lengthByteLength)
	{
		Number offset = ByteUtil.toNumber(Arrays.copyOfRange(bytes, 0, offsetByteLength));
		Number length = null;
		if(lengthByteLength > 0)
		{
			length = ByteUtil.toNumber(Arrays.copyOfRange(bytes, offsetByteLength, offsetByteLength + lengthByteLength));
		}
		return new Position(offset, length);
	}
	
}
