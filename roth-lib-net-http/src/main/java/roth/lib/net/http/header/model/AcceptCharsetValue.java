package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class AcceptCharsetValue implements Characters
{
	protected String charset;
	protected Float quality;
	
	public AcceptCharsetValue(String charset)
	{
		this(charset, null);
	}
	
	public AcceptCharsetValue(String charset, Float quality)
	{
		this.charset = charset;
		this.quality = quality;
	}
	
	public String getCharset()
	{
		return charset;
	}
	
	public Float getQuality()
	{
		return quality;
	}
	
	@Override
	public String toString()
	{
		if(charset != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(charset);
			if(quality != null)
			{
				builder.append(SEMI_COLON);
				builder.append('q');
				builder.append(EQUAL);
				builder.append(String.format("%.1f", quality));
			}
			return builder.toString();
		}
		return null;
	}
	
}
