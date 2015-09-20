package roth.lib.java.outputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import roth.lib.java.util.IoUtil;

public class FileOutputter extends Outputter
{
	protected File file;
	
	public FileOutputter(File file)
	{
		this.file = file;
	}
	
	@Override
	public void output(OutputStream output) throws IOException
	{
		InputStream input = null;
		try
		{
			input = new FileInputStream(file);
			IoUtil.copy(input, output);
		}
		finally
		{
			if(input != null)
			{
				input.close();
			}
		}
	}
	
}
