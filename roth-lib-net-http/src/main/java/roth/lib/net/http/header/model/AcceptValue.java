package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class AcceptValue implements Characters
{
	protected String type;
	protected String subtype;
	protected Float quality;
	
	public AcceptValue(String type, String subtype)
	{
		this(type, subtype, null);
	}
	
	public AcceptValue(String type, String subtype, Float quality)
	{
		this.type = type;
		this.subtype = subtype;
		this.quality = quality;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getSubtype()
	{
		return subtype;
	}
	
	public Float getQuality()
	{
		return quality;
	}
	
	@Override
	public String toString()
	{
		if(type != null && subtype != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(type);
			builder.append(SLASH);
			builder.append(subtype);
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
