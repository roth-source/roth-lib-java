package roth.lib.java.util;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roth.lib.java.Characters;
import roth.lib.java.DataUrl;
import roth.lib.java.type.MimeType;

public class DataUrlUtil implements Characters
{
	protected static String MIME_TYPE = "mimeType";
	protected static String CHARSET = "charset";
	protected static String CHARSET_REPLACE = "charset=";
	protected static String BASE_64 = "base64";
	protected static Pattern pattern = Pattern.compile(String.format("^data:(?<%s>[^;]+?)?(?<%s>;%s\\S+?)?(?<%s>;base64)?,", MIME_TYPE, CHARSET, CHARSET_REPLACE, BASE_64));
	
	protected DataUrlUtil()
	{
		
	}
	
	public static DataUrl parse(String value)
	{
		DataUrl dataUrl = null;
		Matcher matcher = pattern.matcher(value);
		if(matcher.find())
		{
			dataUrl = new DataUrl();
			String mimeTypeCapture = matcher.group(MIME_TYPE);
			if(mimeTypeCapture != null)
			{
				try
				{
					dataUrl.setMimeType(MimeType.fromString(mimeTypeCapture));
				}
				catch(Exception e)
				{
					
				}
			}
			String charsetCapture = matcher.group(CHARSET);
			if(charsetCapture != null)
			{
				try
				{
					dataUrl.setCharset(Charset.forName(charsetCapture.replaceFirst(CHARSET_REPLACE, "")));
				}
				catch(Exception e)
				{
					
				}
			}
			dataUrl.setBase64(matcher.group(BASE_64) != null);
			if(dataUrl.isBase64())
			{
				dataUrl.setBytes(Base64.getDecoder().decode(value.substring(matcher.end())));
			}
			else
			{
				dataUrl.setBytes(value.substring(matcher.end()).getBytes(dataUrl.getCharset() != null ? dataUrl.getCharset() : UTF_8));
			}
		}
		return dataUrl;
	}
	
	public static void main(String[] args)
	{
		DataUrl dataUrl = parse("data:text/plain;base64,dGVzdA==");
		System.out.println(new String(dataUrl.getString()));
	}
	
}
