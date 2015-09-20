package roth.lib.java.api.vultr.client;

@SuppressWarnings("serial")
public class VultrException extends RuntimeException
{
	
	public VultrException(String message)
	{
		super(message);
	}
	
	public VultrException(Throwable cause)
	{
		super(cause);
	}
	
	public VultrException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
