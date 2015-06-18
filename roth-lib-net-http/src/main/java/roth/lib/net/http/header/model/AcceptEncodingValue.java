package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class AcceptEncodingValue implements Characters
{
	protected String encoding;
	protected Float quality;
	
	public AcceptEncodingValue(String encoding)
	{
		this(encoding, null);
	}
	
	public AcceptEncodingValue(String encoding, Float quality)
	{
		this.encoding = encoding;
		this.quality = quality;
	}
	
	public String getEncoding()
	{
		return encoding;
	}
	
	public Float getQuality()
	{
		return quality;
	}
	
	@Override
	public String toString()
	{
		if(encoding != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(encoding);
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
