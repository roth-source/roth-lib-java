package roth.lib.java.form;

import java.io.ByteArrayInputStream;

import roth.lib.java.type.MimeType;

public class FormData
{
	protected String filename;
	protected MimeType contentType;
	protected byte[] bytes;
	
	public FormData(String filename, MimeType contentType, byte[] bytes)
	{
		this.filename = filename;
		this.contentType = contentType;
		this.bytes = bytes;
	}
	
	public String getFilename()
	{
		return filename;
	}
	
	public MimeType getContentType()
	{
		return contentType;
	}
	
	public ByteArrayInputStream getInput()
	{
		return new ByteArrayInputStream(bytes);
	}
	
	public FormData setFilename(String filename)
	{
		this.filename = filename;
		return this;
	}
	
	public FormData setContentType(MimeType contentType)
	{
		this.contentType = contentType;
		return this;
	}
	
	public FormData setBytes(byte[] bytes)
	{
		this.bytes = bytes;
		return this;
	}
	
}
