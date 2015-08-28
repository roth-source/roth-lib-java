package roth.lib.api.digitalocean;

@SuppressWarnings("serial")
public class DigitalOceanException extends RuntimeException
{
	
	public DigitalOceanException(String message)
	{
		super(message);
	}
	
	public DigitalOceanException(Throwable cause)
	{
		super(cause);
	}
	
	public DigitalOceanException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
