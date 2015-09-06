package roth.lib.service;

@SuppressWarnings("serial")
public class HttpServiceException extends RuntimeException
{
	
	public HttpServiceException(String message)
	{
		super(message);
	}
	
	public HttpServiceException(Throwable cause)
	{
		super(cause);
	}
	
	public HttpServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
