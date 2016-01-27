package roth.lib.java.code.java;

import java.io.File;
import roth.lib.java.lang.List;

public class JavaCodeGenerator
{
	
	public static void main(String[] args)
	{
		File dir = new File(System.getProperty("user.dir"));
		List<File> files = getFiles(dir);
		for(File file : files)
		{
			System.out.println(file.getAbsolutePath());
		}
	}
	
	public static List<File> getFiles(File dir)
	{
		List<File> files = new List<File>();
		for(File file : dir.listFiles())
		{
			if(file.isDirectory())
			{
				files.addAll(getFiles(file));
			}
			else if(file.isFile() && !file.isHidden() && file.getName().endsWith(".java"))
			{
				files.add(file);
			}
		}
		return files;
	}
	
}
