package roth.lib.java.http;

@SuppressWarnings("serial")
public class HttpUrlException extends RuntimeException
{
	
	public HttpUrlException(String message)
	{
		super(message);
	}
	
	public HttpUrlException(Throwable cause)
	{
		super(cause);
	}
	
	public HttpUrlException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
