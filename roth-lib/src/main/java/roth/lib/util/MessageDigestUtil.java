package roth.lib.util;

import static roth.lib.util.BaseUtil.BASE_16;
import static roth.lib.util.BaseUtil.BASE_36;
import static roth.lib.util.BaseUtil.BASE_62;

import java.security.MessageDigest;

public class MessageDigestUtil
{
	public static final String MD5 			= "MD5";
	public static final String SHA_1 		= "SHA-1";
	public static final String SHA_256 		= "SHA-256";
	
	protected MessageDigestUtil()
	{
		
	}
	
	public static String digestMd5Base16(String value)
	{
		return StringUtil.padLeftLimit(digest(value, MD5, BASE_16), 32, '0');
	}
	
	public static String digestMd5Base36(String value)
	{
		return digest(value, MD5, BASE_36);
	}
	
	public static String digestMd5Base62(String value)
	{
		return digest(value, MD5, BASE_62);
	}
	
	public static String digestSha1Base16(String value)
	{
		return digest(value, SHA_1, BASE_16);
	}
	
	public static String digestSha1Base36(String value)
	{
		return digest(value, SHA_1, BASE_36);
	}
	
	public static String digestSha1Base62(String value)
	{
		return digest(value, SHA_1, BASE_62);
	}
	
	public static String digestSha256Base16(String value)
	{
		return digest(value, SHA_256, BASE_16);
	}
	
	public static String digestSha256Base36(String value)
	{
		return digest(value, SHA_256, BASE_36);
	}
	
	public static String digestSha256Base62(String value)
	{
		return digest(value, SHA_256, BASE_62);
	}
	
	public static String digest(String value, String algorithm, int base)
	{
		return digest(value.getBytes(), algorithm, base);
	}
	
	public static String digestMd5Base16(byte[] bytes)
	{
		return digest(bytes, MD5, BASE_16);
	}
	
	public static String digestMd5Base36(byte[] bytes)
	{
		return digest(bytes, MD5, BASE_36);
	}
	
	public static String digestMd5Base62(byte[] bytes)
	{
		return digest(bytes, MD5, BASE_62);
	}
	
	public static String digestSha1Base16(byte[] bytes)
	{
		return digest(bytes, SHA_1, BASE_16);
	}
	
	public static String digestSha1Base36(byte[] bytes)
	{
		return digest(bytes, SHA_1, BASE_36);
	}
	
	public static String digestSha1Base62(byte[] bytes)
	{
		return digest(bytes, SHA_1, BASE_62);
	}
	
	public static String digestSha256Base16(byte[] bytes)
	{
		return digest(bytes, SHA_256, BASE_16);
	}
	
	public static String digestSha256Base36(byte[] bytes)
	{
		return digest(bytes, SHA_256, BASE_36);
	}
	
	public static String digestSha256Base62(byte[] bytes)
	{
		return digest(bytes, SHA_256, BASE_62);
	}
	
	public static String digest(byte[] bytes, String algorithm, int base)
	{
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(bytes);
			return BaseUtil.encode(messageDigest.digest(), base);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
