package roth.lib.net.http.header.model;

import roth.lib.Characters;
import roth.lib.net.http.header.type.EncodingType;

public class ContentEncoding extends Header<ContentEncoding> implements Characters
{
	protected String encoding;
	protected EncodingType encodingType;
	
	public ContentEncoding()
	{
		
	}
	
	public ContentEncoding(String encoding)
	{
		this.encoding = encoding;
		this.encodingType = EncodingType.get(encoding);
	}
	
	public String getEncoding()
	{
		return encoding;
	}
	
	public EncodingType getEncodingType()
	{
		return encodingType;
	}
	
	@Override
	public ContentEncoding deserialize(String value)
	{
		return new ContentEncoding(value);
	}
	
	@Override
	public ContentEncoding parse()
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
		else if(encoding != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(encoding);
			return builder.toString();
		}
		return null;
	}
	
}
