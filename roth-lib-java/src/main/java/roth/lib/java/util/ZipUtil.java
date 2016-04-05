package roth.lib.java.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import roth.lib.java.lang.List;

public class ZipUtil
{
	
	protected ZipUtil()
	{
		
	}
	
	public static File zip(File dir) throws IOException
	{
		File archive = new File(dir.getParentFile(), dir.getName() + ".zip");
		zip(dir, archive);
		return archive;
	}
	
	public static void zip(File dir, File archive) throws IOException
	{
		List<File> files = DirUtil.files(dir);
		if(!files.isEmpty())
		{
			try(ZipOutputStream output = new ZipOutputStream(new FileOutputStream(archive));)
			{
				for(File file : files)
				{
					output.putNextEntry(new ZipEntry(FileUtil.relative(dir, file)));
					FileUtil.copy(file, output);
					output.closeEntry();
				}
				
			}
		}
		else
		{
			throw new FileNotFoundException("no files found in dir " + dir.getAbsolutePath());
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		File dir = new File("/Users/User/Downloads/test");
		zip(dir);
	}
	
}
