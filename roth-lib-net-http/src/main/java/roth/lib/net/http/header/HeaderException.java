package roth.lib.net.http.header;

@SuppressWarnings("serial")
public class HeaderException extends RuntimeException
{
	
	public HeaderException(String message)
	{
		super(message);
	}
	
	public HeaderException(Throwable cause)
	{
		super(cause);
	}
	
	public HeaderException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
