package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class ContentDisposition extends Header<ContentDisposition> implements Characters
{
	protected String type;
	protected String filename;
	
	public ContentDisposition()
	{
		
	}
	
	public ContentDisposition(String value)
	{
		this.value = value;
	}
	
	public ContentDisposition(String type, String filename)
	{
		this.type = type;
		this.filename = filename;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getFilename()
	{
		return filename;
	}
	
	@Override
	public ContentDisposition deserialize(String value)
	{
		return new ContentDisposition(value);
	}
	
	@Override
	public ContentDisposition parse()
	{
		return this;
	}
	
	@Override
	public String toString()
	{
		if(value != null)
		{
			return value;
		}
		else if(type != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(type);
			if(filename != null)
			{
				builder.append(SEMI_COLON);
				builder.append(SPACE);
				builder.append("filename");
				builder.append(EQUAL);
				builder.append(QUOTE);
				builder.append(filename);
				builder.append(QUOTE);
			}
			return builder.toString();
		}
		return null;
	}
	
}
