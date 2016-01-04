package roth.lib.java.code.java;

import java.io.File;
import java.util.LinkedList;

public class JavaCodeGenerator
{
	
	public static void main(String[] args)
	{
		File dir = new File(System.getProperty("user.dir"));
		LinkedList<File> files = getFiles(dir);
		for(File file : files)
		{
			System.out.println(file.getAbsolutePath());
		}
	}
	
	public static LinkedList<File> getFiles(File dir)
	{
		LinkedList<File> files = new LinkedList<File>();
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
