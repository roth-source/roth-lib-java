package roth.lib.map.serializer;

import java.io.IOException;
import java.io.OutputStream;

import roth.lib.map.Serializer;

public class BytesSerializer extends Serializer<Object>
{
	protected byte[] bytes;
	
	public BytesSerializer(byte[] bytes)
	{
		this.bytes = bytes;
	}
	
	@Override
	public void serialize(OutputStream output) throws IOException
	{
		output.write(bytes);
	}
	
}
