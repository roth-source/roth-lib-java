package roth.lib.map.xml.soap;

import roth.lib.annotation.Property;
import roth.lib.map.xml.XmlModel;

@SuppressWarnings("serial")
public class SoapFault extends XmlModel
{
	@Property(name = "faultcode")
	protected String faultCode;
	
	@Property(name = "faultstring")
	protected String faultString;
	
	@Property(name = "faultactor")
	protected String faultActor;
	
	@Property(name = "detail")
	protected String detail;
	
	public SoapFault()
	{
		
	}
	
	public String getFaultCode()
	{
		return faultCode;
	}
	
	public String getFaultString()
	{
		return faultString;
	}
	
	public String getFaultActor()
	{
		return faultActor;
	}
	
	public String getDetail()
	{
		return detail;
	}
	
	public SoapFault setFaultCode(String faultCode)
	{
		this.faultCode = faultCode;
		return this;
	}
	
	public SoapFault setFaultString(String faultString)
	{
		this.faultString = faultString;
		return this;
	}
	
	public SoapFault setFaultActor(String faultActor)
	{
		this.faultActor = faultActor;
		return this;
	}
	
	public SoapFault setDetail(String detail)
	{
		this.detail = detail;
		return this;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		String seperator = "";
		if(faultCode != null)
		{
			builder.append(faultCode);
			seperator = " : ";
		}
		if(faultString != null)
		{
			builder.append(seperator);
			builder.append(faultString);
			seperator = " : ";
		}
		if(faultActor != null)
		{
			builder.append(seperator);
			builder.append(faultActor);
			seperator = " : ";
		}
		if(detail != null)
		{
			builder.append(seperator);
			builder.append(detail);
		}
		return builder.toString();
	}
	
}
