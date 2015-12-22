package roth.lib.java.xml.soap;

@SuppressWarnings("serial")
public class SoapException extends RuntimeException
{
	protected SoapFault fault;

	public SoapException(SoapFault fault)
	{
		super(fault.toString());
		this.fault = fault;
	}
	
	public SoapException(String message)
	{
		super(message);
	}
	
	public SoapException(Throwable cause)
	{
		super(cause);
	}
	
	public SoapException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public SoapFault getFault()
	{
		return fault;
	}
	
}
