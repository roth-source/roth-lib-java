package roth.lib.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtil
{
	
	protected FileUtil()
	{
		
	}
	
	public static byte[] toBytes(File file)
	{
		byte[] bytes = null;
		if(file != null && file.exists() && file.isFile())
		{
			try(FileInputStream input = new FileInputStream(file);)
			{
				bytes = IoUtil.toBytes(input);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return bytes;
	}
	
	public static String toString(File file)
	{
		String contents = null;
		byte[] bytes = toBytes(file);
		if(bytes != null)
		{
			contents = new String(bytes);
		}
		return contents;
	}

	public static void copy(File file, OutputStream output) throws IOException
	{
		try(FileInputStream input = new FileInputStream(file))
		{
			IoUtil.copy(input, output);
		}
	}
	
	public static String relative(File baseDir, File file)
	{
		return baseDir.toPath().relativize(file.toPath()).toString();
	}
	
}
