package roth.lib.java.api.twilio;

@SuppressWarnings("serial")
public class TwilioException extends RuntimeException
{
	
	public TwilioException(String message)
	{
		super(message);
	}
	
	public TwilioException(Throwable cause)
	{
		super(cause);
	}
	
	public TwilioException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
