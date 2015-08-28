package roth.lib.map.outputter;

import java.io.IOException;
import java.io.OutputStream;

public class BytesOutputter extends Outputter
{
	protected byte[] bytes;
	
	public BytesOutputter(byte[] bytes)
	{
		this.bytes = bytes;
	}
	
	@Override
	public void output(OutputStream output) throws IOException
	{
		output.write(bytes);
	}
	
}
