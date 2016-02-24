package roth.lib.java.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MacUtil
{
	public static final String HMAC_MD5 		= "HmacMD5";
	public static final String HMAC_SHA_1 		= "HmacSHA1";
	public static final String HMAC_SHA_256 	= "HmacSHA256";
	
	protected MacUtil()
	{
		
	}
	
	public static byte[] hmacMd5(String key, String value)
	{
		return hmac(key, value, HMAC_MD5);
	}
	
	public static byte[] hmacMd5(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5);
	}
	
	public static byte[] hmacSha1(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_1);
	}
	
	public static byte[] hmacSha1(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1);
	}
	
	public static byte[] hmacSha256(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_256);
	}
	
	public static byte[] hmacSha256(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256);
	}

	public static byte[] hmacMd5(byte[] key, String value)
	{
		return hmac(key, value, HMAC_MD5);
	}
	
	public static byte[] hmacMd5(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5);
	}
	
	public static byte[] hmacSha1(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_1);
	}
	
	public static byte[] hmacSha1(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1);
	}
	
	public static byte[] hmacSha256(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_256);
	}
	
	public static byte[] hmacSha256(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256);
	}
	
	public static byte[] hmac(String key, String value, String algorithm)
	{
		return hmac(key.getBytes(), value.getBytes(), algorithm);
	}
	
	public static byte[] hmac(String key, byte[] bytes, String algorithm)
	{
		return hmac(key.getBytes(), bytes, algorithm);
	}
	
	public static byte[] hmac(byte[] key, String value, String algorithm)
	{
		return hmac(key, value.getBytes(), algorithm);
	}
	
	public static byte[] hmac(byte[] key, byte[] bytes, String algorithm)
	{
		try
		{
			Mac mac = Mac.getInstance(algorithm);
			mac.init(new SecretKeySpec(key, algorithm));
			mac.update(bytes);
			return mac.doFinal();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static String hmacMd5Base16(String key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_16);
	}
	
	public static String hmacMd5Base36(String key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_36);
	}
	
	public static String hmacMd5Base62(String key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_62);
	}
	
	public static String hmacMd5Base64(String key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_64);
	}
	
	public static String hmacSha1Base16(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_16);
	}
	
	public static String hmacSha1Base36(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_36);
	}
	
	public static String hmacSha1Base62(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_62);
	}
	
	public static String hmacSha1Base64(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_64);
	}
	
	public static String hmacSha256Base16(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_16);
	}
	
	public static String hmacSha256Base36(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_36);
	}
	
	public static String hmacSha256Base62(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_62);
	}
	
	public static String hmacSha256Base64(String key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_64);
	}
	
	public static String hmac(String key, String value, String algorithm, int base)
	{
		return hmac(key, value.getBytes(), algorithm, base);
	}
	
	public static String hmacMd5Base16(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_16);
	}
	
	public static String hmacMd5Base36(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_36);
	}
	
	public static String hmacMd5Base62(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_62);
	}
	
	public static String hmacMd5Base64(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_64);
	}
	
	public static String hmacSha1Base16(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_16);
	}
	
	public static String hmacSha1Base36(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_36);
	}
	
	public static String hmacSha1Base62(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_62);
	}
	
	public static String hmacSha1Base64(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_64);
	}
	
	public static String hmacSha256Base16(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_16);
	}
	
	public static String hmacSha256Base36(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_36);
	}
	
	public static String hmacSha256Base62(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_62);
	}
	
	public static String hmacSha256Base64(String key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_64);
	}
	
	public static String hmac(String key, byte[] bytes, String algorithm, int base)
	{
		return hmac(key.getBytes(), bytes, algorithm, base);
	}
	
	public static String hmacMd5Base16(byte[] key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_16);
	}
	
	public static String hmacMd5Base36(byte[] key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_36);
	}
	
	public static String hmacMd5Base62(byte[] key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_62);
	}
	
	public static String hmacMd5Base64(byte[] key, String value)
	{
		return hmac(key, value, HMAC_MD5, BaseUtil.BASE_64);
	}
	
	public static String hmacSha1Base16(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_16);
	}
	
	public static String hmacSha1Base36(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_36);
	}
	
	public static String hmacSha1Base62(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_62);
	}
	
	public static String hmacSha1Base64(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_1, BaseUtil.BASE_64);
	}
	
	public static String hmacSha256Base16(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_16);
	}
	
	public static String hmacSha256Base36(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_36);
	}
	
	public static String hmacSha256Base62(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_62);
	}
	
	public static String hmacSha256Base64(byte[] key, String value)
	{
		return hmac(key, value, HMAC_SHA_256, BaseUtil.BASE_64);
	}
	
	public static String hmac(byte[] key, String value, String algorithm, int base)
	{
		return hmac(key, value.getBytes(), algorithm, base);
	}
	
	public static String hmacMd5Base16(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_16);
	}
	
	public static String hmacMd5Base36(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_36);
	}
	
	public static String hmacMd5Base62(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_62);
	}
	
	public static String hmacMd5Base64(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_MD5, BaseUtil.BASE_64);
	}
	
	public static String hmacSha1Base16(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_16);
	}
	
	public static String hmacSha1Base36(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_36);
	}
	
	public static String hmacSha1Base62(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_62);
	}
	
	public static String hmacSha1Base64(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_1, BaseUtil.BASE_64);
	}
	
	public static String hmacSha256Base16(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_16);
	}
	
	public static String hmacSha256Base36(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_36);
	}
	
	public static String hmacSha256Base62(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_62);
	}
	
	public static String hmacSha256Base64(byte[] key, byte[] bytes)
	{
		return hmac(key, bytes, HMAC_SHA_256, BaseUtil.BASE_64);
	}
	
	public static String hmac(byte[] key, byte[] bytes, String algorithm, int base)
	{
		return BaseUtil.encode(hmac(key, bytes, algorithm), base);
	}
	
}
