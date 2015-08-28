package roth.lib.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import roth.lib.Characters;

public class IoUtil
{
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	
	protected IoUtil()
	{
		
	}
	
	public static long copy(InputStream input, OutputStream output) throws IOException
	{
		return copy(input, output, new byte[DEFAULT_BUFFER_SIZE]);
	}
	
	public static long copy(InputStream input, OutputStream output, byte[] buffer) throws IOException
	{
		long count = 0;
		int n = 0;
		while((n = input.read(buffer)) != -1)
		{
			output.write(buffer, 0, n);
			count += n;
		}
		output.flush();
		return count;
	}
	
	public static long copy(Reader reader, Writer writer) throws IOException
	{
		return copy(reader, writer, new char[DEFAULT_BUFFER_SIZE]);
	}
	
	public static long copy(Reader reader, Writer writer, char[] buffer) throws IOException
	{
		long count = 0;
		int n = 0;
		while((n = reader.read(buffer)) != -1)
		{
			writer.write(buffer, 0, n);
			count += n;
		}
		writer.flush();
		return count;
	}
	
	public static byte[] toBytes(InputStream input) throws IOException
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}
	
	public static String toString(InputStream input) throws IOException
	{
		return new String(toBytes(input), Characters.UTF_8);
	}
	
	public static String toString(Reader reader) throws IOException
	{
		StringWriter writer = new StringWriter();
		copy(reader, writer);
		return writer.toString();
	}
	
}
