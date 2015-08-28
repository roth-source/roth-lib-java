package roth.lib.api.rackspace;

@SuppressWarnings("serial")
public class RackspaceException extends RuntimeException
{
	
	public RackspaceException(String message)
	{
		super(message);
	}
	
	public RackspaceException(Throwable cause)
	{
		super(cause);
	}
	
	public RackspaceException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
