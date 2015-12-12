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
	
	public static byte[] compress(String contents) throws IOException
	{
		return compress(contents.getBytes());
	}
	
	public static byte[] compress(byte[] bytes) throws IOException
	{
		return compress(new ByteArrayInputStream(bytes));
	}
	
	public static byte[] compress(InputStream input) throws IOException
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutput = new GZIPOutputStream(output);
		IoUtil.copy(input, gzipOutput);
		gzipOutput.finish();
		return output.toByteArray();
	}
	
	public static String decompressContents(byte[] bytes) throws IOException
	{
		return decompressContents(new ByteArrayInputStream(bytes));
	}
	
	public static String decompressContents(InputStream input) throws IOException
	{
		return new String(decompressBytes(input), Characters.UTF_8);
	}
	
	public static byte[] decompressBytes(byte[] bytes) throws IOException
	{
		return decompressBytes(new ByteArrayInputStream(bytes));
	}
	
	public static byte[] decompressBytes(InputStream input) throws IOException
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		IoUtil.copy(new GZIPInputStream(input), output);
		return output.toByteArray();
	}
	
}
