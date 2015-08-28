package roth.lib.api.cloudflare;

@SuppressWarnings("serial")
public class CloudFlareException extends RuntimeException
{
	
	public CloudFlareException(String message)
	{
		super(message);
	}
	
	public CloudFlareException(Throwable cause)
	{
		super(cause);
	}
	
	public CloudFlareException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
