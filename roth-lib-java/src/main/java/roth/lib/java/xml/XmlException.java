package roth.lib.java.xml;

@SuppressWarnings("serial")
public class XmlException extends RuntimeException
{
	
	public XmlException(String message)
	{
		super(message);
	}
	
	public XmlException(Throwable cause)
	{
		super(cause);
	}
	
	public XmlException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
