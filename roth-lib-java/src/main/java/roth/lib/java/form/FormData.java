package roth.lib.java.form;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FormData
{
	protected String filename;
	protected String contentType;
	protected ByteArrayOutputStream output;
	
	public FormData(String filename, String contentType, ByteArrayOutputStream output)
	{
		this.filename = filename;
		this.contentType = contentType;
		this.output = output;
	}
	
	public FormData(String filename, String contentType, byte[] bytes)
	{
		this.filename = filename;
		this.contentType = contentType;
		setBytes(bytes);
	}
	
	public String getFilename()
	{
		return filename;
	}
	
	public String getContentType()
	{
		return contentType;
	}
	
	public ByteArrayOutputStream getOutput()
	{
		return output;
	}
	
	public FormData setFilename(String filename)
	{
		this.filename = filename;
		return this;
	}
	
	public FormData setContentType(String contentType)
	{
		this.contentType = contentType;
		return this;
	}
	
	public FormData setOutput(ByteArrayOutputStream output)
	{
		this.output = output;
		return this;
	}
	
	public FormData setBytes(byte[] bytes)
	{
		this.output = new ByteArrayOutputStream();
		try
		{
			output.write(bytes);
		}
		catch(IOException e)
		{
			
		}
		return this;
	}
	
}
