package roth.lib.java.util;

import java.io.File;
import java.util.LinkedList;

public class ClassLoaderUtil
{
	
	protected ClassLoaderUtil()
	{
		
	}
	
	public static LinkedList<Class<?>> getClasses()
	{
		LinkedList<Class<?>> classes = new LinkedList<Class<?>>();
		for(String path : System.getProperty("java.class.path").split(File.pathSeparator))
		{
			if(!path.toLowerCase().endsWith(".jar"))
			{
				classes.addAll(getClasses(new File(path)));
			}
		}
		return classes;
	}
	
	protected static LinkedList<Class<?>> getClasses(File dir)
	{
		LinkedList<Class<?>> classes = new LinkedList<Class<?>>();
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
	
	protected static LinkedList<String> getClassNames(File dir)
	{
		return getClassNames(dir, "");
	}
	
	protected static LinkedList<String> getClassNames(File dir, String path)
	{
		LinkedList<String> classNames = new LinkedList<String>();
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
