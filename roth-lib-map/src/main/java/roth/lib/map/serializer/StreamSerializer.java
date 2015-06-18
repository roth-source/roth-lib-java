package roth.lib.map.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import roth.lib.map.Serializer;
import roth.lib.util.IoUtil;

public class StreamSerializer extends Serializer<Object>
{
	protected InputStream input;
	
	public StreamSerializer(InputStream input)
	{
		this.input = input;
	}
	
	@Override
	public void serialize(OutputStream output) throws IOException
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
