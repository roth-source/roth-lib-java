package roth.lib.java.xml.soap;

import roth.lib.java.annotation.Entity;
import roth.lib.java.annotation.Property;
import roth.lib.java.xml.XmlModel;

@Entity(name = "soap:Envelope")
@SuppressWarnings("serial")
public class SoapEnvelope<T> extends XmlModel
{
	@Property(name = "soap:Header")
	protected SoapHeader header;
	
	@Property(name = "soap:Body")
	protected T body;
	
	public SoapEnvelope()
	{
		
	}
	
	public SoapHeader getHeader()
	{
		return header;
	}
	
	public T getBody()
	{
		return body;
	}
	
	public SoapEnvelope<T> setHeader(SoapHeader header)
	{
		this.header = header;
		return this;
	}
	
	public SoapEnvelope<T> setBody(T body)
	{
		this.body = body;
		return this;
	}
	
}
