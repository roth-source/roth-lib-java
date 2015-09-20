package roth.lib.java.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil
{
	
	protected PropertiesUtil()
	{
		
	}
	
	public static Properties load(File file)
	{
		try(FileReader reader = new FileReader(file))
		{
			Properties properties = new Properties();
			properties.load(reader);
			return properties;
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static Properties load(String resource)
	{
		try(InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(resource))
		{
			Properties properties = new Properties();
			properties.load(input);
			return properties;
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
