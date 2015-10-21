package roth.lib.java.map.form;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;

public class FormFile
{
	protected File file;
	protected String filename;
	protected long offset = 0;
	protected Long length;
	
	public FormFile(File file)
	{
		this.file = file;
	}
	
	public File getFile()
	{
		return file;
	}
	
	public String getFilename()
	{
		return filename != null ? filename : file.getName();
	}
	
	public Long getOffset()
	{
		return offset;
	}
	
	public Long getLength()
	{
		return length != null ? length : file.length();
	}
	
	public String getContentType()
	{
		String contentType = "";
		try
		{
			contentType = Files.probeContentType(file.toPath());
		}
		catch(Exception e)
		{
			
		}
		if(contentType == null)
		{
			contentType = URLConnection.guessContentTypeFromName(file.getName());
		}
		return contentType;
	}
	
	public FormFile setFile(File file)
	{
		this.file = file;
		return this;
	}
	
	public FormFile setFilename(String filename)
	{
		this.filename = filename;
		return this;
	}
	
	public FormFile setOffset(long offset)
	{
		this.offset = offset;
		return this;
	}
	
	public FormFile setLength(Long length)
	{
		this.length = length;
		return this;
	}
	
}
