package roth.lib.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import roth.lib.java.lang.List;

public class ResourceUtil
{
	
	protected ResourceUtil()
	{
		
	}
	
	public static String toString(String path)
	{
		return toString(path, ResourceUtil.class.getClassLoader());
	}
	
	public static String toString(String path, ClassLoader classLoader)
	{
		return new String(toBytes(path));
	}
	
	public static byte[] toBytes(String path)
	{
		return toBytes(path, ResourceUtil.class.getClassLoader());
	}
	
	public static byte[] toBytes(String path, ClassLoader classLoader)
	{
		try(InputStream input = classLoader.getResourceAsStream(path))
		{
			return IoUtil.toBytes(input);
		}
		catch(IOException e)
		{
			return new byte[0];
		}
	}
	
	public static List<String> listPaths(String path)
	{
		return listPaths(path, ResourceUtil.class.getClassLoader());
	}
	
	public static List<String> listPaths(String path, ClassLoader classLoader)
	{
		List<String> paths = new List<String>();
		if(classLoader.getResource(path) != null)
		{
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(path)));)
			{
				String line = null;
				while((line = reader.readLine()) != null)
				{
					paths.add(line);
				}
			}
			catch(IOException e)
			{
				
			}
		}
		return paths;
	}
	
}
