package roth.lib.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import roth.lib.java.type.AlgorithmModeType;
import roth.lib.java.type.AlgorithmPaddingType;
import roth.lib.java.type.AlgorithmType;

public class CipherUtil
{
	
	protected CipherUtil()
	{
		
	}
	
	public static Cipher encryptionCipher(String key, AlgorithmType algorithm)
	{
		return cipher(key, algorithm, true);
	}
	
	public static Cipher decryptionCipher(String key, AlgorithmType algorithm)
	{
		return cipher(key, algorithm, false);
	}
	
	protected static Cipher cipher(String key, AlgorithmType algorithm, boolean encrypt)
	{
		try
		{
			int opmode = encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
			SecretKey secretKey = new SecretKeySpec(padKey(key, algorithm.getKeyLength()), algorithm.getName());
			Cipher cipher = Cipher.getInstance(algorithm.getAlgorithm(AlgorithmPaddingType.PKCS5PADDING));
			if(AlgorithmModeType.CBC.equals(algorithm.getAlgorithmModeType()))
			{
				cipher.init(opmode, secretKey, new IvParameterSpec(padKey(key, algorithm.getIvLength())));
			}
			else
			{
				cipher.init(opmode, secretKey);
			}
			return cipher;
		}
		catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	protected static byte[] padKey(String key, int length)
	{
		byte[] keyBytes = key.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(length);
		for(int i = 0; i < length; i++)
		{
			buffer.put(keyBytes[i % key.length()]);
		}
		return buffer.array();
	}
	
	public static byte[] encrypt(String value, String key, AlgorithmType algorithm)
	{
		return encrypt(value.getBytes(), key, algorithm);
	}
	
	public static byte[] encrypt(byte[] bytes, String key, AlgorithmType algorithm)
	{
		try
		{
			Cipher cipher = encryptionCipher(key, algorithm);
			return cipher.doFinal(bytes);
		}
		catch(BadPaddingException | IllegalBlockSizeException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] decrypt(String value, String key, AlgorithmType algorithm)
	{
		return decrypt(value.getBytes(), key, algorithm);
	}
	
	public static byte[] decrypt(byte[] bytes, String key, AlgorithmType algorithm)
	{
		try
		{
			Cipher cipher = decryptionCipher(key, algorithm);
			return cipher.doFinal(bytes);
		}
		catch(BadPaddingException | IllegalBlockSizeException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static OutputStream encrypt(OutputStream output, String key, AlgorithmType algorithm)
	{
		return new CipherOutputStream(output, encryptionCipher(key, algorithm));
	}
	
	public static OutputStream decrypt(OutputStream output, String key, AlgorithmType algorithm)
	{
		return new CipherOutputStream(output, decryptionCipher(key, algorithm));
	}
	
	public static InputStream encrypt(InputStream input, String key, AlgorithmType algorithm)
	{
		return new CipherInputStream(input, encryptionCipher(key, algorithm));
	}
	
	public static InputStream decrypt(InputStream input, String key, AlgorithmType algorithm)
	{
		return new CipherInputStream(input, decryptionCipher(key, algorithm));
	}
	
	public static void encrypt(InputStream input, OutputStream output, String key, AlgorithmType algorithm)
	{
		try
		{
			output = encrypt(output, key, algorithm);
			IoUtil.copy(input, output);
			output.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void decrypt(InputStream input, OutputStream output, String key, AlgorithmType algorithm)
	{
		try
		{
			output = decrypt(output, key, algorithm);
			IoUtil.copy(input, output);
			output.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void encrypt(File inputFile, File outputFile, String key, AlgorithmType algorithm)
	{
		try(InputStream input = new FileInputStream(inputFile); OutputStream output = new FileOutputStream(outputFile))
		{
			encrypt(input, output, key, algorithm);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void decrypt(File inputFile, File outputFile, String key, AlgorithmType algorithm)
	{
		try(InputStream input = new FileInputStream(inputFile); OutputStream output = new FileOutputStream(outputFile))
		{
			decrypt(input, output, key, algorithm);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		String key = "12345";
		//String value = "test";
		//System.out.println(value);
		//System.out.println(new String(decrypt(encrypt(value, key, AlgorithmType.AES_CBC), key, AlgorithmType.AES_CBC)));
		///*
		File file = new File("/Users/User/Downloads/test.txt");
		File encryptedFile = new File("/Users/User/Downloads/test-encrypted");
		File decryptedFile = new File("/Users/User/Downloads/test-decrypted.txt");
		encrypt(file, encryptedFile, key, AlgorithmType.AES_CBC);
		decrypt(encryptedFile, decryptedFile, key, AlgorithmType.AES_CBC);
		//*/
	}
	
}
