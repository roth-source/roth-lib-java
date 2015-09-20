package roth.lib.java.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import roth.lib.java.Characters;

public class GzipUtil
{
	
	protected GzipUtil()
	{
		
	}
	
	public static byte[] compress(String contents)
	{
		return compress(contents.getBytes());
	}
	
	public static byte[] compress(byte[] bytes)
	{
		return compress(new ByteArrayInputStream(bytes));
	}
	
	public static byte[] compress(InputStream input)
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try
		{
			GZIPOutputStream gzipOutput = new GZIPOutputStream(output);
			IoUtil.copy(input, gzipOutput);
			gzipOutput.finish();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return output.toByteArray();
	}
	
	public static String decompressContents(byte[] bytes)
	{
		return decompressContents(new ByteArrayInputStream(bytes));
	}
	
	public static String decompressContents(InputStream input)
	{
		return new String(decompressBytes(input), Characters.UTF_8);
	}
	
	public static byte[] decompressBytes(byte[] bytes)
	{
		return decompressBytes(new ByteArrayInputStream(bytes));
	}
	
	public static byte[] decompressBytes(InputStream input)
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try
		{
			IoUtil.copy(new GZIPInputStream(input), output);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return output.toByteArray();
	}
	
}
