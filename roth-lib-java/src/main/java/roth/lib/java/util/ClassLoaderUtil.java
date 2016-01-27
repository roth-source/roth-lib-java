package roth.lib.java.util;

import java.io.File;

import roth.lib.java.lang.List;

public class ClassLoaderUtil
{
	
	protected ClassLoaderUtil()
	{
		
	}
	
	public static List<Class<?>> getClasses()
	{
		List<Class<?>> classes = new List<Class<?>>();
		for(String path : System.getProperty("java.class.path").split(File.pathSeparator))
		{
			if(!path.toLowerCase().endsWith(".jar"))
			{
				classes.addAll(getClasses(new File(path)));
			}
		}
		return classes;
	}
	
	protected static List<Class<?>> getClasses(File dir)
	{
		List<Class<?>> classes = new List<Class<?>>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for(String className : getClassNames(dir))
		{
			try
			{
				classes.add(classLoader.loadClass(className));
			}
			catch(Exception e)
			{
				
			}
		}
		return classes;
	}
	
	protected static List<String> getClassNames(File dir)
	{
		return getClassNames(dir, "");
	}
	
	protected static List<String> getClassNames(File dir, String path)
	{
		List<String> classNames = new List<String>();
		for(File file : dir.listFiles())
		{
			if(file.isFile() && file.getName().endsWith(".class"))
			{
				classNames.add(path + file.getName().replaceFirst(".class$", ""));
			}
			else if(file.isDirectory())
			{
				classNames.addAll(getClassNames(file, path + file.getName() + "."));
			}
		}
		return classNames;
	}
	
}
