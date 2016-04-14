package roth.lib.java.util;

import java.io.File;

import roth.lib.java.lang.List;

public class DirUtil
{
	
	protected DirUtil()
	{
		
	}
	
	public static List<File> files(File dir)
	{
		List<File> files = new List<>();
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				files.addAll(files(file));
			}
			else if(file.isFile())
			{
				files.add(file);
			}
		}
		return files;
	}
	
	public static void delete(File dir)
	{
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				delete(file);
			}
			else
			{
				file.delete();
			}
		}
		dir.delete();
	}
	
}
