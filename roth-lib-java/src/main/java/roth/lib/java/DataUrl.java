package roth.lib.java;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Base64;

import roth.lib.java.type.MimeType;

@SuppressWarnings("serial")
public class DataUrl implements Serializable, Characters
{
	protected MimeType mimeType;
	protected Charset charset = UTF_8;
	boolean base64 = true;
	byte[] bytes;
	
	public DataUrl()
	{
		
	}
	
	public MimeType getMimeType()
	{
		return mimeType;
	}
	
	public Charset getCharset()
	{
		return charset;
	}
	
	public boolean isBase64()
	{
		return base64;
	}
	
	public byte[] getBytes()
	{
		return bytes;
	}
	
	public String getString()
	{
		return new String(bytes, charset != null ? charset : UTF_8);
	}
	
	public DataUrl setMimeType(MimeType mimeType)
	{
		this.mimeType = mimeType;
		return this;
	}
	
	public DataUrl setCharset(Charset charset)
	{
		this.charset = charset;
		return this;
	}
	
	public DataUrl setBase64(boolean base64)
	{
		this.base64 = base64;
		return this;
	}
	
	public DataUrl setBytes(byte[] bytes)
	{
		this.bytes = bytes;
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("data:");
		if(mimeType != null)
		{
			builder.append(mimeType.toString());
		}
		if(charset != null)
		{
			builder.append(";charset=");
			builder.append(charset.name());
		}
		if(base64)
		{
			builder.append(";base64");
		}
		builder.append(",");
		if(base64)
		{
			builder.append(new String(Base64.getEncoder().encode(bytes), charset != null ? charset : UTF_8));
		}
		else
		{
			builder.append(new String(bytes, charset != null ? charset : UTF_8));
		}
		return builder.toString();
	}
	
}
