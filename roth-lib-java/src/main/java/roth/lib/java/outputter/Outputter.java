package roth.lib.java.outputter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;

import roth.lib.java.Characters;

public abstract class Outputter
{
	protected boolean gzip;
	
	protected Outputter()
	{
		
	}
	
	public boolean isGzip()
	{
		return gzip;
	}
	
	public Outputter setGzip(boolean gzip)
	{
		this.gzip = gzip;
		return this;
	}
	
	public void wrap(OutputStream output) throws IOException
	{
		if(gzip)
		{
			GZIPOutputStream gzipOutput = new GZIPOutputStream(output);
			output(gzipOutput);
			gzipOutput.finish();
		}
		else
		{
			output(output);
		}
	}
	
	public void output(OutputStream output) throws IOException
	{
		output(new OutputStreamWriter(output, Characters.UTF_8));
	}
	
	public void output(Writer writer) throws IOException
	{
		output(new BufferedWriter(writer));
	}
	
	public void output(BufferedWriter writer) throws IOException
	{
		
	}
	
}
