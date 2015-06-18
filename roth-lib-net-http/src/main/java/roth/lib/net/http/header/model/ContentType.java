package roth.lib.net.http.header.model;

import roth.lib.Characters;
import roth.lib.net.http.header.type.MimeType;

public class ContentType extends Header<ContentType> implements Characters
{
	protected MimeType mimeType;
	protected String mime;
	protected String charset;
	
	public ContentType()
	{
		
	}
	
	public ContentType(String value)
	{
		this.value = value;
	}
	
	public ContentType(String mime, String charset)
	{
		this.mimeType = MimeType.get(mime);
		this.mime = mime;
		this.charset = charset;
	}
	
	public ContentType(MimeType mimeType)
	{
		this(mimeType, null);
	}
	
	public ContentType(MimeType mimeType, String charset)
	{
		this.mimeType = mimeType;
		this.mime = mimeType.getValue();
		this.charset = charset;
	}
	
	public MimeType getMimeType()
	{
		return mimeType;
	}
	
	public String getMime()
	{
		return mime;
	}
	
	public String getCharset()
	{
		return charset;
	}
	
	@Override
	public ContentType deserialize(String value)
	{
		return new ContentType(value);
	}
	
	@Override
	public ContentType parse()
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
		else if(mime != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(mime);
			if(charset != null)
			{
				builder.append(SEMI_COLON);
				builder.append("charset");
				builder.append(EQUAL);
				builder.append(charset);
			}
			return builder.toString();
		}
		return null;
	}
	
}
