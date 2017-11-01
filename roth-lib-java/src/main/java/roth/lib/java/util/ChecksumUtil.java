package roth.lib.java.util;

import java.util.zip.CRC32;

public class ChecksumUtil
{
	
	protected ChecksumUtil()
	{
		
	}
	
	public static long checksumCrc32(String value)
	{
		return checksumCrc32(value.getBytes());
	}
	
	public static long checksumCrc32(byte[] bytes)
	{
		try
		{
			CRC32 crc32 = new CRC32();
			crc32.update(bytes);
			return crc32.getValue();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
