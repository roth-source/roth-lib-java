package roth.lib.map.outputter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import roth.lib.util.IoUtil;

public class StreamOutputter extends Outputter
{
	protected InputStream input;
	
	public StreamOutputter(InputStream input)
	{
		this.input = input;
	}
	
	@Override
	public void output(OutputStream output) throws IOException
	{
		try
		{
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
