package roth.lib.net.http.header.model;

import roth.lib.Characters;

public class AcceptLanguageValue implements Characters
{
	protected String language;
	protected Float quality;
	
	public AcceptLanguageValue(String language)
	{
		this(language, null);
	}
	
	public AcceptLanguageValue(String language, Float quality)
	{
		this.language = language;
		this.quality = quality;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public Float getQuality()
	{
		return quality;
	}
	
	@Override
	public String toString()
	{
		if(language != null)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(language);
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
