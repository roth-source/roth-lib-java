package roth.lib.java.net.http;

@SuppressWarnings("serial")
public class HttpException extends RuntimeException
{
	
	public HttpException(String message)
	{
		super(message);
	}
	
	public HttpException(Throwable cause)
	{
		super(cause);
	}
	
	public HttpException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
