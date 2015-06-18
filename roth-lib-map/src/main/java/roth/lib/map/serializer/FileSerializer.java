package roth.lib.map.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import roth.lib.map.Serializer;
import roth.lib.util.IoUtil;

public class FileSerializer extends Serializer<Object>
{
	protected File file;
	
	public FileSerializer(File file)
	{
		this.file = file;
	}
	
	@Override
	public void serialize(OutputStream output) throws IOException
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
