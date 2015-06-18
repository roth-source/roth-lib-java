package roth.lib.net.http.api;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException
{
	
	public ApiException(String message)
	{
		super(message);
	}
	
	public ApiException(Throwable cause)
	{
		super(cause);
	}
	
	public ApiException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
